/**
 * Created by М on 13.05.2016.
 */

'use strict';

app.directive("hashTag", function(){
    return {
        restrict: "E",
        template: "<span>{{ tag.name }} <span title='Delete this tag' class='deleteTagBtn' ng-click='deleteHashTag($index)'>x</span></span>"
    }
});
