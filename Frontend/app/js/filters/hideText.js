/**
 * Created by М on 18.05.2016.
 */

app.filter('hideText', function(){
   return function (value, max){
       if (value.length > max) return value.substring(0, max) + "...";
       else return value;
   }
});