package com.ymy.graduation.service;

import com.ymy.graduation.domain.Repository;

import java.util.List;

/**
 * @author ymyum
 * @date 2020/2/5 15:34
 * @project
 */
public interface RepositoryService {
    /**创建*/
    String CreateRepository(Repository repository);
    /**删除*/
    String DeleteRepository(Long repoId);
    /**查找*/
    Repository findByRepoId(Long repoId);
    List<Repository> findAllRepository();
//    List<Repository> findByRepoDate(Date repoDate);
    /**更改*/
    Boolean UpdateRepository(String repoState, String repoItem, Integer repoNum, Integer repoUpNum, Double repoFee, String repoName,Long repoId);
    Boolean UpdateRepositoryNum(Integer repoNum,Long repoId);
}
