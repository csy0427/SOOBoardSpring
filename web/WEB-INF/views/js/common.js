
function checkImageType(fileName) {
    var pattern=/jpg|gif|png|jpeg|i;
    return fileName.match(pattern)
}

function getFileInfo(fullName){
    var fileName,imgsrc,getLink,fileLink;
    if(checkImageType(fullName)) {
        imgsrc="/board/ajaxUpload?fileName="+fullName;
        console.log(imgsrc);

        fileLink=fullName.substr(14);
        console.log(fileLink);

        var front=fullName.substr(0,12);
        console.log(front);

        var end=fullName.substr(14);
        console.log(end);

        getLink="/board/ajaxUpload?fileName="+front+end;
        console.log(getLink);
    }
    else{
        fileLink=fullName.substr(12);
        console.log(fileLink);

        getLink="/board/ajaxUpload?fileName="+fullName;
        console.log(getLink);
    }

    fileName=fileLink.substr(fileLink.indexOf("_")+1);
    console.log(fileName);
    return{fileName:fileName, imgsrc:imgsrc, getLink:getLink, fullName:fullName};
}

$(document).ready(function(){
    // 파일 업로드 영역에서 기본효과를 제한
    $(".fileDrop").on("dragenter dragover", function(e){
        e.preventDefault(); // 기본효과 제한
    });
    // 드래그해서 드롭한 파일들 ajax 업로드 요청
    $(".fileDrop").on("drop", function(e){
        e.preventDefault(); // 기본효과 제한
        var files = e.originalEvent.dataTransfer.files; // 드래그한 파일들
        var file = files[0]; // 첫번째 첨부파일
        var formData = new FormData(); // 폼데이터 객체
        formData.append("file", file); // 첨부파일 추가
        $.ajax({
            url: "/file/ajaxUpload.do",
            type: "post",
            data: formData,
            dataType: "text",
            processData: false, // processType: false - header가 아닌 body로 전달
            contentType: false,
            // ajax 업로드 요청이 성공적으로 처리되면
            success: function(data){
                console.log(data);
                // 첨부 파일의 정보
                var fileInfo = getFileInfo(data);
                // 하이퍼링크
                var html = "<a href='"+fileInfo.getLink+"'>"+fileInfo.fileName+"</a><br>";
                // hidden 태그 추가
                html += "<input type='hidden' class='file' value='"+fileInfo.fullName+"'>";
                // div에 추가
                $("#uploadedList").append(html);
            }
        });
    });
}

$(".uploadedList").on("click", "span", function(event){
    alert("이미지 삭제")
    var that = $(this); // 여기서 this는 클릭한 span태그
    $.ajax({
        url: "${path}/upload/deleteFile",
        type: "post",
        // data: "fileName="+$(this).attr("date-src") = {fileName:$(this).attr("data-src")}
        // 태그.attr("속성")
        data: {fileName:$(this).attr("data-src")}, // json방식
        dataType: "text",
        success: function(result){
            if( result == "deleted" ){
                // 클릭한 span태그가 속한 div를 제거
                that.parent("div").remove();
            }
        }
    });
});