package org.blog.service.impl;

import org.blog.dao.IpActionDao;
import org.blog.model.IpAction;
import org.blog.service.IpActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class IpActionServiceImpl implements IpActionService {

    @Autowired
    private IpActionDao ipActionDao;

    @Override
    public Integer count() {
        return ipActionDao.count();
    }

    @Override
    public List<IpAction> list(String ip) {
        return ipActionDao.list(ip);
    }

    @Override
    public List<Map<String,Object>> ipActionCount(String ip) {
        return ipActionDao.ipActionCount(ip);
    }

    @Override
    public void save(IpAction ipAction) {
        ipActionDao.save(ipAction);
    }
}
