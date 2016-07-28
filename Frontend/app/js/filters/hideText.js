'use strict';

app.filter('hideText', function(){
   return function (value, max){
       if (value.length > max) return value.substring(0, max) + "...";
       else return value;
   }
});