//<script type="text/javascript" th:inline="javascript"></script>
    function pokazi() {
      document.getElementById("myDropdown").classList.toggle("show")
    }
    
    window.onclick = function(event) {
      if (!event.target.matches('.dropbtn') && event.target.matches('.tad')) {
        //alert(event.target.id)
        var dropdowns = document.getElementsByClassName("dropdown-content");
        var box = document.getElementById("text");
        box.innerHTML = document.getElementById(event.target.id).innerHTML;
        var i;
        for (i = 0; i < dropdowns.length; i++) {
          var openDropdown = dropdowns[i];

          if (openDropdown.classList.contains('show')) {
            openDropdown.classList.remove('show');
          }
        }
      }
      else if(event.target.matches('.dropbtn')){
        var dropdowns = document.getElementsByClassName("dropdown-content")[0].childNodes;

        for (i = 0; i < dropdowns.length; i++) {
          var openDropdown = dropdowns[i];

          //alert(openDropdown.style.backgroundColor)

          openDropdown.style.backgroundColor = increase_brightness('#' + Math.floor(Math.random()*16777215).toString(16), 90);
          openDropdown.style.color = invertColor('#' + Math.floor(Math.random()*16777215).toString(16));
        }
      }
    }

    function invertColor(hex) {
      if (hex.indexOf('#') === 0) {
          hex = hex.slice(1);
      }
      // convert 3-digit hex to 6-digits.
      if (hex.length === 3) {
          hex = hex[0] + hex[0] + hex[1] + hex[1] + hex[2] + hex[2];
      }
      // invert color components
      var r = (255 - parseInt(hex.slice(0, 2), 16)).toString(16),
          g = (255 - parseInt(hex.slice(2, 4), 16)).toString(16),
          b = (255 - parseInt(hex.slice(4, 6), 16)).toString(16);
      // pad each with zeros and return
      return decrease_brightness('#' + padZero(r) + padZero(g) + padZero(b), 50);
  }

  function padZero(str, len) {
    len = len || 2;
    var zeros = new Array(len).join('0');
    return (zeros + str).slice(-len);
  }

  function increase_brightness(hex, percent){
    hex = hex.replace(/^\s*#|\s*$/g, '');

    if(hex.length == 3){
        hex = hex.replace(/(.)/g, '$1$1');
    }

    var r = parseInt(hex.substr(0, 2), 16),
        g = parseInt(hex.substr(2, 2), 16),
        b = parseInt(hex.substr(4, 2), 16);

    return '#' +
       ((0|(1<<8) + r + (256 - r) * percent / 100).toString(16)).substr(1) +
       ((0|(1<<8) + g + (256 - g) * percent / 100).toString(16)).substr(1) +
       ((0|(1<<8) + b + (256 - b) * percent / 100).toString(16)).substr(1);
  }

  function decrease_brightness(hex, percent){
    hex = hex.replace(/^\s*#|\s*$/g, '');

    if(hex.length == 3){
        hex = hex.replace(/(.)/g, '$1$1');
    }

    var r = parseInt(hex.substr(1, 2), 16),
        g = parseInt(hex.substr(3, 2), 16),
        b = parseInt(hex.substr(5, 2), 16);

   return '#' +
       ((0|(1<<8) + r * (100 - percent) / 100).toString(16)).substr(1) +
       ((0|(1<<8) + g * (100 - percent) / 100).toString(16)).substr(1) +
       ((0|(1<<8) + b * (100 - percent) / 100).toString(16)).substr(1);
  }

  var colors = ["red", "orange", "yellow", "green", "blue", "purple"];
  var currentIndex = 0;
  var inter
  
    inter = setInterval(function() {
      try{
        document.getElementById("dropbtn").style.cssText = "color: " + colors[currentIndex];
        currentIndex++;
        if (currentIndex == undefined || currentIndex >= colors.length) {
          currentIndex = 0;
        }
      }
      catch{
        clearInterval(inter)
      }
    }, 1000);