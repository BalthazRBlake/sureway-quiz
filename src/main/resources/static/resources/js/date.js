const startBtn = document.getElementById('startBtn');

loadEventListeners();

function loadEventListeners(){
    startBtn.addEventListener('click', getTime);
}

function getTime(e){
    let date = new Date();
    sessionStorage.setItem("date", date);
}