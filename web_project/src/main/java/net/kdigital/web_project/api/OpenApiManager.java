package net.kdigital.web_project.api;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.kdigital.web_project.dto.TaxDTO;

@Component
public class OpenApiManager {

    private static String getTagValue(String tag, Element eElement) {
        NodeList nodeList = eElement.getElementsByTagName(tag);
        if (nodeList != null && nodeList.getLength() > 0) {
            NodeList nlList = nodeList.item(0).getChildNodes();
            if (nlList != null && nlList.getLength() > 0) {
                Node nValue = nlList.item(0);
                if (nValue != null) {
                    return nValue.getNodeValue();
                }
            }
        }
        return null;
    }

    public List<TaxDTO> taxOpenApi(String hsAll) {
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, String>> list = new ArrayList<>();
        String key = "x220d224k064z055q000i000u0";

        try {
            String url = "https://unipass.customs.go.kr:38010/ext/rest/hsSgnQry/searchHsSgn?"
                    + "crkyCn=" + key // 인증키
                    + "&hsSgn=" + hsAll // 입력 받은 hs code
                    + "&koenTp=1";

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(url);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("hsSgnSrchRsltVo");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Map<String, String> map = new HashMap<>();

                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    // 각 hsSgnSrchRsltVo 요소에서 필요한 데이터 가져오기
                    map.put("qtyUt", getTagValue("qtyUt", eElement));
                    map.put("hsSgn", getTagValue("hsSgn", eElement));
                    map.put("wghtUt", getTagValue("wghtUt", eElement));
                    map.put("englPrnm", getTagValue("englPrnm", eElement));
                    map.put("txrt", getTagValue("txrt", eElement));
                    map.put("korePrnm", getTagValue("korePrnm", eElement));
                    map.put("txtpSgn", getTagValue("txtpSgn", eElement));
                    list.add(map);
                }
            }
            System.out.println(list);

            // List<Map> to List<Dto>
            List<TaxDTO> dtoList = mapper.convertValue(list, mapper.getTypeFactory().constructCollectionType(List.class, TaxDTO.class));
            System.out.println(dtoList.get(0).getHsSgn() + dtoList.get(0).getTxtpSgn() + dtoList.get(0).getTxrt());

            return dtoList;

        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public String refundsOpenApi(String hsAll) {
        String key = "j210k284u074o078h000d030b0";
        String refundsWon = "";

        try {
            String url = "https://unipass.customs.go.kr:38010/ext/rest/simlXamrttXtrnUserQry/retrieveSimlXamrttXtrnUser?"
                    + "crkyCn=" + key // 인증키
                    + "&baseDt=20240101"
                    + "&hsSgn=" + hsAll;

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(url);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("simlXamrttXtrnUserQryRsltVo");

            if (nList.getLength() > 0) {
                Node nNode = nList.item(0);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    // 각 simlXamrttXtrnUserQryRsltVo 요소에서 필요한 데이터 가져오기
                    //String hsCode = getTagValue("hs10", eElement);
                    refundsWon = getTagValue("prutDrwbWncrAmt", eElement);
                }
            }
            System.out.println("단위당환급원화금액 : " + refundsWon);
            return refundsWon;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<String> exOpenApi(String hsAll) {
        String key = "l250r204o024n155l040m050b0";
        List<String> exCheckList = new ArrayList<>();

        try {
            String url = "https://unipass.customs.go.kr:38010/ext/rest/ccctLworCdQry/retrieveCcctLworCd?"
                    + "crkyCn=" + key // 인증키
                    + "&hsSgn=" + hsAll
                    + "&imexTp=1";

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(url);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("ccctLworCdQryRtnVo");

            if (nList.getLength() > 0) {
                Node nNode = nList.item(0);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    // 각 ccctLworCdQryRtnVo 요소에서 필요한 데이터 가져오기
                    System.out.println("신고인확인법령명 : " + getTagValue("dcerCfrmLworNm", eElement));
                    System.out.println("요건확인서류명 : " + getTagValue("reqCfrmIstmNm", eElement));
                    exCheckList.add(getTagValue("dcerCfrmLworNm", eElement));
                    exCheckList.add(getTagValue("reqCfrmIstmNm", eElement));
                }
            }
            return exCheckList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<String> imOpenApi(String hsAll) {
        String key = "l250r204o024n155l040m050b0";
        List<String> imCheckList = new ArrayList<>();

        try {
            String url = "https://unipass.customs.go.kr:38010/ext/rest/ccctLworCdQry/retrieveCcctLworCd?"
                    + "crkyCn=" + key // 인증키
                    + "&hsSgn=" + hsAll
                    + "&imexTp=2";

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(url);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("ccctLworCdQryRtnVo");

            if (nList.getLength() > 0) {
                Node nNode = nList.item(0);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    // 각 ccctLworCdQryRtnVo 요소에서 필요한 데이터 가져오기
                    System.out.println("신고인확인법령명 : " + getTagValue("dcerCfrmLworNm", eElement));
                    System.out.println("요건확인서류명 : " + getTagValue("reqCfrmIstmNm", eElement));
                    imCheckList.add(getTagValue("dcerCfrmLworNm", eElement));
                    imCheckList.add(getTagValue("reqCfrmIstmNm", eElement));
                }
            }
            return imCheckList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // 관세사부호 조회
    public List<String> CCAOpenApi(String ccaNum) {
        String key = "k280h294z014d068h040p090i0";
        List<String> ccaList = new ArrayList<>();

        try {
            String url = "https://unipass.customs.go.kr:38010/ext/rest/lcaBrkdQry/retrieveLcaBrkd?"
                    + "crkyCn=" + key // 인증키
                    + "&lcaSgn=" + ccaNum;
  

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(url);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("lcaBrkdQryRtnVo");
            System.out.println(nList.toString());
            System.out.println(nList.getLength());

            if (nList.getLength() > 0) {
                Node nNode = nList.item(0);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.println(nNode);
                    // 각 lcaBrkdQryRsltVo 요소에서 필요한 데이터 가져오기
                    System.out.println("회사명 : " + getTagValue("lcaConm", eElement));
                    System.out.println("전화번호 : " + getTagValue("telno", eElement));
                    System.out.println("주소 : " + getTagValue("addr", eElement));       
                    ccaList.add(getTagValue("jrsdCstmNm", eElement));
                    ccaList.add(getTagValue("lcaSgn", eElement));
                    ccaList.add(getTagValue("lcaConm", eElement));
                    ccaList.add(getTagValue("telno", eElement));
                    ccaList.add(getTagValue("addr", eElement));
                }
            }
            return ccaList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    // 환율
    public List<Map<String, String>> exchangeRateOpenApi(String today, String imexTP) {
        String key = "q270q264p024s098v060x060p0";
        List<Map<String, String>> exchangeRateList = new ArrayList<>();

        try {
            String url = "https://unipass.customs.go.kr:38010/ext/rest/trifFxrtInfoQry/retrieveTrifFxrtInfo?"
                    + "crkyCn=" + key // 인증키
                    + "&qryYymmDd=" + today
                    + "&imexTp=" + imexTP;

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(url);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("trifFxrtInfoQryRsltVo");
            System.out.println("Total elements: " + nList.getLength());

            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    Map<String, String> exchangeRate = new HashMap<>();
                    exchangeRate.put("CountrySign", getTagValue("cntySgn", eElement));
                    exchangeRate.put("CurrencyUnitName", getTagValue("mtryUtNm", eElement));
                    exchangeRate.put("ExchangeRate", getTagValue("fxrt", eElement));
                    exchangeRate.put("CurrencySign", getTagValue("currSgn", eElement));
                    exchangeRate.put("ImportExportType", getTagValue("imexTp", eElement));

                    exchangeRateList.add(exchangeRate);
                }
            }

            return exchangeRateList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
