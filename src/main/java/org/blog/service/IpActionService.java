package org.blog.service;

import org.blog.model.IpAction;

import java.util.List;
import java.util.Map;

public interface IpActionService {
    Integer count();

    List<IpAction> list(String ip);

    List<Map<String, Object>> ipActionCount(String ip);

    void save(IpAction ipAction);
}
