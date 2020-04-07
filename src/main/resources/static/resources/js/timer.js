let date = new Date(sessionStorage.getItem("date"));
//console.log(date);
//console.log(date.getTime());
let finalTime = date.getTime() + 30 * 60000;
//console.log(finalTime);
var x = setInterval(function() {

  var now = new Date().getTime();
  var distance = finalTime - now;

  var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
  var seconds = Math.floor((distance % (1000 * 60)) / 1000);

  document.getElementById("timer").innerHTML = minutes + "m " + seconds + "s ";

  if (distance < 0) {
    clearInterval(x);
    document.getElementById("timer").innerHTML = "TIME UP!!!";
    window.location.href = '/agent/quiz/result';
  }
}, 1000);
