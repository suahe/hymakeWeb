package com.hymake.mobileWeb.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hymake.mobileWeb.entity.SearchEntity;
import com.hymake.mobileWeb.mapper.SearchMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author suahe
 * @date 2020/10/28 16:39
 * @describe TODO
 */
@Service("SearchService")
public class SearchService {

    @Autowired
    private SearchMapper searchMapper;

    public PageInfo<SearchEntity> search(Integer offset, Integer limit, String keyword) {
        String source = "";
        PageHelper.offsetPage(offset, limit);
        keyword = keyword.replace(" ", "");
        PageInfo<SearchEntity> pi = new PageInfo<SearchEntity>(searchMapper.selectPagination(keyword));
        List<SearchEntity> list = pi.getList();
        for (SearchEntity searchEntity : list) {
            String title = searchEntity.getTitle();
            String content = searchEntity.getContent();
            if (StringUtils.isNotBlank(title)){
                title = getReplaceStr(title,keyword);
                searchEntity.setTitle(title);
            }
            if (StringUtils.isNotBlank(content)){
                content = getReplaceStr(content,keyword);
                searchEntity.setContent(content);
            }
            if("新闻".equals(searchEntity.getSource())&&StringUtils.isEmpty(searchEntity.getUrl())){
                searchEntity.setUrl("/news/view/"+searchEntity.getId());
            }
        }
        pi.setList(list);
        return pi;
    }

    public String getReplaceStr(String str, String keyword) {
        Pattern p = Pattern.compile(keyword, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(str);
        String group = "";
        if(m.find()&&StringUtils.isEmpty(group)){
            group = m.group();
        }
        String result = m.replaceAll("<font color='#FF7723'>" + group + "</font>");
        return result;
    }
}
