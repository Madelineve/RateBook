'use strict';

var singleUploadForm = document.querySelector('#singleUploadForm');
var singleFileUploadInput = document.querySelector('#singleFileUploadInput');
var singleFileUploadError = document.querySelector('#singleFileUploadError');
var singleFileUploadSuccess = document.querySelector('#singleFileUploadSuccess');


function uploadSingleFile(file, title,  authorName,authorSurname ,description,category) {

    var formData = new FormData();

    formData.append("file", file);
    formData.append("title",title);
    formData.append("authorName",authorName);
    formData.append("authorSurname",authorSurname);
    formData.append("description",description);
    formData.append("category",category);

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/uploadFile");

    xhr.onload = function() {
        console.log(xhr.responseText);
        var response = JSON.parse(xhr.responseText);
        if(xhr.status == 200) {
            singleFileUploadError.style.display = "none";
            singleFileUploadSuccess.style.display = "block";
        } else {
            singleFileUploadSuccess.style.display = "none";
            singleFileUploadError.innerHTML = (response && response.message) || "Some Error Occurred";
        }
    }
    xhr.send(formData);
}


singleUploadForm.addEventListener('submit', function(event){
    var files = singleFileUploadInput.files;
    var title = String(document.getElementById("title").value);
    var authorName = String(document.getElementById("authorName").value);
    var authorSurname = String(document.getElementById("authorSurname").value);
    var description = String(document.getElementById("description").value);
    var category = String(document.getElementById("category").value);
    if(files.length === 0) {
        singleFileUploadError.innerHTML = "Please select a file";
        singleFileUploadError.style.display = "block";
    }
    uploadSingleFile(files[0], title, authorName,authorSurname ,description,category);
    event.preventDefault();
}, true);


