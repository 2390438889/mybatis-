package lujie.dao;

import lujie.model.Resume;

public interface ResumeMapper {
    int insert(Resume record);

    int insertSelective(Resume record);
}