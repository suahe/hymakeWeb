
var basetype = {
		select: function(_id, _code, _width) {
			if(undefined == _width){
				_width = 200;
			}
        	$("#"+_id).select2({
        		language: "zh-CN",
        		theme: "bootstrap",
        		width: _width,
        		placeholder: "请选择",
        		allowClear: true,
        		minimumResultsForSearch: -1,
        		ajax: {
        			url: '../btc/getListByCode',
        			type: "GET",
        			dataType: 'json',
        			data: function (params) {
						return {
							code: _code
						};
    			    },
        			processResults: function(data){
        				return {
        					results: data
        			    };
        			}
        		}
            });
		},
		selectName: function(_code, _value){
			
			var data = '未知';
			$.ajax({
                type: 'GET',
                async: false,
                url: '../btc/getListNameByCodeAndValue',
                data: {'code': _code, 'value': _value},
                dataType: 'json',
                success: function(r){
                	data = r.name;
                }
			});
			return data;
		},
		selectByCodeAndValue: function(_id, _code, _value){
			
			var data = '未知';
			$.ajax({
                type: 'GET',
                async: false,
                url: '../btc/getListNameByCodeAndValue',
                data: {'code': _code, 'value': _value},
                dataType: 'json',
                success: function(r){
                	var option = '<option value="'+ r.value +'">'+r.name+'</option>'
                	$('#'+_id).append(option);
                }
			});
			return data;
		},
		tree: function(_id, _code){
			
			$.get('../btc/getTreeByCode', {'code': _code},function(r){
				var data = JSON.stringify(r);
				$('#'+_id).treeview({
					selectedBackColor: '#1ab394',
					data:data,
					enableLinks:true,
					levels:4
				});
			})
		},
		treeName(_code, _value){
			
			var data = '未知';
			$.ajax({
                type: 'GET',
                async: false,
                url: '../btc/getTreeNameByCodeAndValue',
                data: {'code': _code, 'value': _value},
                dataType: 'json',
                success: function(r){
                	data = r.name;
                }
			});
			return data;
		},
		treeFullName(_code, _value){
			
			var data = '未知';
			$.ajax({
                type: 'GET',
                async: false,
                url: '../btc/getTreeNameByCodeAndValue',
                data: {'code': _code, 'value': _value},
                dataType: 'json',
                success: function(r){
                	data = r.fullName;
                }
			});
			return data;
		}
}