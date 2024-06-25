$(function(){
    $("#categorySearchBtn").on('click', categorySearch);
});

function categorySearch() {
    const searchItem = $("select[name='searchItem']").val(); // 선택된 값을 변수에 저장
    const contextPath = $("#contextPath").val(); 	
  
    $.ajax({
        method : 'GET',
        url : contextPath+"/cca/boardList",
        data: {
            "searchItem": searchItem
        },
        success : function(response) {
            // AJAX 요청이 성공했을 때의 동작
            console.log(response); // 예시: 서버로부터 받은 응답을 콘솔에 출력
            // 원하는 추가 동작을 여기에 작성
        }
    });
}

