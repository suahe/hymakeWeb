/**
 * getLevel:判断当前节点属于树的第几级，目前支持5级，可以自行添加，代码有点恶心。
 * */
var treeview = {
		getLevel: function(tree, treeData){
			var level = 0;
			var parentData = tree.treeview('getParent', treeData[0].nodeId);
			if(typeof(parentData.id) == "undefined"){
				level = 1;
			}else{
				parentData = tree.treeview('getParent', parentData.nodeId);
				if(typeof(parentData.id) == "undefined"){
					level = 2;
				}else{
					parentData = tree.treeview('getParent', parentData.nodeId);
					if(typeof(parentData.id) == "undefined"){
						level = 3;
					}else{
						parentData = tree.treeview('getParent', parentData.nodeId);
						if(typeof(parentData.id) == "undefined"){
							level = 4;
						}else{
							parentData = tree.treeview('getParent', parentData.nodeId);
							if(typeof(parentData.id) == "undefined"){
								level = 5;
							}
						}
					}
				}
			}
			return level;
		}
}