
var message = {
		saveSuccess: function(){
			swal({
				title: "系统提示",
				text: "保存数据成功。",
				type: "success",
				confirmButtonColor: "#1AB394",
				confirmButtonText: "确定",
				timer: 800
			});
		},
		saveFall: function(){
			swal({
				title: "系统提示",
				text: "保存数据失败，请联系管理员！",
				type: "error",
				confirmButtonColor: "#1AB394",
				confirmButtonText: "确定",
			});
		},
		loadSuccess: function(){
			swal({
				title: "系统提示",
				text: "加载数据成功。",
				type: "success",
				confirmButtonColor: "#1AB394",
				confirmButtonText: "确定",
				timer: 800
			});
		},
		loadFall: function(){
			swal({
				title: "系统提示",
				text: "加载数据失败，请联系管理员！",
				type: "error",
				confirmButtonColor: "#1AB394",
				confirmButtonText: "确定",
			});
		},
		selectOne: function(){
			swal({
				title: "系统提示",
				text: "请选择一条记录。",
				type: "info",
				confirmButtonColor: "#1AB394",
				confirmButtonText: "确定",
				timer: 2000
			});
		},
		delSuccess: function(){
			swal({
				title: "删除成功!",
				text: "您所选中的记录删除成功。",
				type: "success",
				confirmButtonColor: "#1AB394",
				confirmButtonText: "确定",
				timer: 800
			});
		},
		delFall: function(){
			swal({
				title: "删除失败!",
				text: "您所选中的记录删除失败，请联系管理员。",
				type: "error",
				confirmButtonColor: "#1AB394",
				confirmButtonText: "确定"
			});
		},
		success: function(text){
			swal({
				title: "系统提示",
				text: text,
				type: "success",
				confirmButtonColor: "#1AB394",
				confirmButtonText: "确定",
				timer: 800
			});
		},
		info: function(text){
			swal({
				title: "系统提示",
				text: text,
				type: "info",
				confirmButtonColor: "#1AB394",
				confirmButtonText: "确定",
				timer: 2000
			});
		},
		warning: function(text){
			swal({
				title: "系统提示",
				text: text,
				type: "warning",
				confirmButtonColor: "#1AB394",
				confirmButtonText: "确定"
			});
		},
		error: function(text){
			swal({
				title: "系统提示",
				text: text,
				type: "error",
				confirmButtonColor: "#1AB394",
				confirmButtonText: "确定"
			});
		}
}