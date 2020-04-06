loadEventListeners();

function loadEventListeners(){
    document.addEventListener('DOMContentLoaded', loaded);
}

function loaded(){
    console.log('asdfas');
}

var hash = window.location.hash;
setInterval(function(){
    if (window.location.hash != hash) {
        hash = window.location.hash;
        alert("User went back or forward to application state represented by " + hash);
    }
}, 100);