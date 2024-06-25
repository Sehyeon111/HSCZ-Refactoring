$(function(){
    // search-item 값이 변경될 때마다 ccaSearch 함수를 호출합니다.
    $("#search-item").on('change', ccaSearch);
});

function ccaSearch() {
    const searchBy = $("#search-type").val(); // 선택된 검색 방법
    const searchItem = $("#search-item").val(); // 선택된 검색 항목
    const contextPath = $("#contextPath").val(); // 컨텍스트 경로를 가져옵니다.

    $.ajax({
        method: 'GET',
        url: contextPath + "/cca/ccaList",
        data: {
            "searchBy": searchBy, // 검색 방법
            "searchItem": searchItem // 검색 항목
        },
        success: function(response) {
            // AJAX 요청이 성공했을 때의 동작
            console.log(response); // 서버로부터 받은 응답을 콘솔에 출력
        },
        error: function(xhr, status, error) {
            // AJAX 요청이 실패했을 때의 동작
            console.error("Error: " + error); // 오류 로그 출력
        }
    });
}

