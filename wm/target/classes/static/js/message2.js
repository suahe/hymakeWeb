
var message = {
		saveSuccess: function(){
			bootbox.alert({
				size: "small",
				title: "系统提示",
				message: "保存数据成功！",
				timer: 1500
			});
		},
		saveFall: function(){
			bootbox.alert({
				size: "small",
				title: "系统提示",
				message: "保存数据失败，请联系管理员！",
				timer: 1500
			});
		},
		loadSuccess: function(){
			bootbox.alert({
				size: "small",
				title: "系统提示",
				message: "加载数据成功！",
				timer: 1500
			});
		},
		loadFall: function(){
			bootbox.alert({
				size: "small",
				title: "系统提示",
				message: "加载数据失败，请联系管理员！",
				timer: 1500
			});
		},
		selectOne: function(){
			bootbox.alert({
				size: "small",
				title: "系统提示",
				message: "请选择一条记录！",
				timer: 1500
			})
		},
		delSuccess: function(){
			swal({
				title: "删除成功!",
				text: "您所选中的记录删除成功。",
				type: "success",
				confirmButtonColor: "#1AB394",
				confirmButtonText: "确定",
				timer: 1500
			});
		},
		delFall: function(){
			swal({
				title: "删除失败!",
				text: "您所选中的记录删除失败，请联系管理员。",
				type: "error",
				confirmButtonColor: "#1AB394",
				confirmButtonText: "确定",
				timer: 1500
			});
		}
}