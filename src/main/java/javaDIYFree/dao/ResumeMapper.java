package javaDIYFree.dao;

import javaDIYFree.model.Resume;

public interface ResumeMapper {
    int insert(Resume record);

    int insertSelective(Resume record);
}