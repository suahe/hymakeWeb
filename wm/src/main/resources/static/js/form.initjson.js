(function($){
    $.fn.extend({
        initjson: function(options){
            var defaults = {
                jsonValue: options,
                isDebug: false
            }
            var setting = defaults;
            var form = this;
            jsonValue = setting.jsonValue;
            if($.type(setting.jsonValue) === "string"){
                jsonValue = $.parseJSON(jsonValue);
            } 
            if(!$.isEmptyObject(jsonValue)){
                var debugInfo = "";
                $.each(jsonValue,function(key,value){
                    if(setting.isDebug){
                        alert("name:"+key+"; value:"+value);
                        debugInfo += "name:"+key+"; value:"+value+" || ";
                    }
                    var formField = form.find("[name='"+key+"']");
                    if($.type(formField[0]) === "undefined"){
                        if(setting.isDebug){
                            alert("can not find name:["+key+"] in form!!!");
                        }
                    } else {
                        var fieldTagName = formField[0].tagName.toLowerCase();
                        if(fieldTagName == "input"){
                            if(formField.attr("type") == "radio"){
                            	//alert('name='+key+'value='+value);
                                $("input:radio[name='"+key+"'][value='"+value+"']").prop("checked",true);
                            } else {
                                formField.val(value);
                            }
                        } else if(fieldTagName == "select"){
                            formField.val(value);
                        } else if(fieldTagName == "textarea"){
                            formField.val(value);
                        } else {
                            formField.val(value);
                        }
                    }
                });
                if(setting.isDebug){  
                    alert(debugInfo);  
                }
            }
            return form;
        }
    });
})(jQuery)