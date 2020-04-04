/*if (window.performance) {
  console.info("window.performance works fine on this browser");
}*/
/*  if (performance.navigation.type == 1) {
    alert('Ending Quiz');
    console.info( "This page is reloaded" );
  }/* else {
    console.info( "This page is not reloaded");
  }*/

  /*
  window.onload = function () {
      if (typeof history.pushState === "function") {
          history.pushState("jibberish", null, null);
          window.onpopstate = function () {
              history.pushState('newjibberish', null, null);
              // Handle the back (or forward) buttons here
              // Will NOT handle refresh, use onbeforeunload for this.
          };
      }
      else {
          var ignoreHashChange = true;
          window.onhashchange = function () {
              if (!ignoreHashChange) {
                  ignoreHashChange = true;
                  window.location.hash = Math.random();
                  // Detect and redirect change here
                  // Works in older FF and IE9
                  // * it does mess with your hash symbol (anchor?) pound sign
                  // delimiter on the end of the URL
              }
              else {
                  ignoreHashChange = false;
              }
          };
      }
  }
*/