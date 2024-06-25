from fastapi import FastAPI
import uvicorn
from pydantic import BaseModel
from starlette.responses import JSONResponse
from nltk.corpus import stopwords
from nltk.tokenize import word_tokenize
from nltk.stem import WordNetLemmatizer
import nltk
import pickle
import numpy as np
from fastapi import Body
import googletrans
from googletrans import Translator
# pip install googletrans==4.0.0rc1 로 install

# cd '현재경로\Predict\model'로 터미널에서 변경해야 실행됨


# NLTK 리소스 다운로드
nltk.download('punkt')
nltk.download('wordnet')
nltk.download('averaged_perceptron_tagger')
nltk.download("stopwords")

# 모델 및 벡터화기 로드
with open('lsvc_tfidf_0322_ssh.pkl', 'rb') as tfidf:
    vectorizer = pickle.load(tfidf)

with open('0404_extended_label_mapping.pkl', 'rb') as label_map:
    label_mapping = pickle.load(label_map)

with open('lsvc_0322_ssh.pkl', 'rb') as lsvc_model:
    model_lsvc = pickle.load(lsvc_model)

def translate_description(description):
    translator = Translator()
    translated_description = translator.translate(description, src=translator.detect(description).lang, dest='en').text
    return translated_description

def preprocess_description(translated_description):
    stop_words = stopwords.words('english')
    stop_words.append('x')
    stop_words.append('w')
    
    tokens = word_tokenize(translated_description.lower())
    tagged_tokens = nltk.pos_tag(tokens)

    lemmatizer = WordNetLemmatizer()
    filtered_tokens = []
    for word, tag in tagged_tokens:
        if (tag.startswith('NN') or tag.startswith('VB')) and word not in stop_words:
            lemma = lemmatizer.lemmatize(word, pos='n' if tag.startswith('NN') else 'v')
            filtered_tokens.append(lemma)
        elif word.isalpha() and word not in stop_words:
            filtered_tokens.append(word)
    preprocessed_description = [' '.join(filtered_tokens)]
    
    return preprocessed_description

def predict_hscode(preprocessed_description):
    description_tfidf_vec = vectorizer.transform(preprocessed_description)
    calibrated_probabilities = model_lsvc.decision_function(description_tfidf_vec)

    lsvc_top3_list = []
    proba_list = calibrated_probabilities.argsort().flatten().tolist()[::-1][:3]
    for i in proba_list:
        for k, v in label_mapping.items():
            if k == i:
                lsvc_top3_list.append(str(v))
    print("예측 top3: ", lsvc_top3_list)
    predicted_hscode = "/".join(lsvc_top3_list)
    return predicted_hscode

# Model 생성
class Item(BaseModel):
    description : str

# app 개발
app = FastAPI()

@app.post("/", status_code=201)
    
def myhscode(item : Item):
    
    description = item.description
    translated_description = translate_description(description)
    preprocessed_description = preprocess_description(translated_description)
    predicted_hscode = predict_hscode(preprocessed_description)
    print("hhhh", predicted_hscode)

    result = {"predicted_hscode": str(predicted_hscode)}  

    return JSONResponse(result)

if __name__ == '__main__':
    uvicorn.run(app, host="127.0.0.1", port=8555)