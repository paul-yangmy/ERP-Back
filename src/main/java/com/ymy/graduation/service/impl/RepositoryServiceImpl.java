package com.ymy.graduation.service.impl;

import com.ymy.graduation.domain.Repository;
import com.ymy.graduation.repository.repositoryTab;
import com.ymy.graduation.service.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ymyum
 * @date 2020/2/5 15:37
 * @project
 */
@Service
public class RepositoryServiceImpl implements RepositoryService {
    @Autowired
    private repositoryTab repositoryTab;
    @Override
    public String CreateRepository(Repository repository) {
        Repository repository1= repositoryTab.findByRepoId(repository.getRepoId());
        if(repository1!=null){
            return "Exists";//存在同名
        }
        else {
            boolean flag=false;
            try {
                repositoryTab.save(repository);
                flag=true;
            }
            catch (Exception e){
                System.out.println(e);
            }
            if(flag){
                return "true";
            }
            else {
                return "false";
            }
        }
    }

    @Override
    public String DeleteRepository(Long repoId) {
        Repository repository= repositoryTab.findByRepoId(repoId);
        if(repository==null){
            return "null";
        }
        else
        {
            boolean flag=false;
            try {
                repositoryTab.delete(repository);
                flag=true;
            }
            catch (Exception e){
                System.out.println(e);
            }
            if(flag){
                return "true";
            }
            else {
                return "false";
            }
        }
    }

    @Override
    public Repository findByRepoId(Long repoId) {
        return repositoryTab.findByRepoId(repoId);
    }
//    @Override
//    public List<Repository> findByRepoDate(Date repoDate) {
//        return repositoryTab.findByRepoDate(repoDate);
//    }

    @Override
    public List<Repository> findAllRepository() {
        return repositoryTab.findAll();
    }

    @Override
    public Boolean UpdateRepository(String repoState, String repoItem, Integer repoNum, Integer repoUpNum, Double repoFee, String repoName,Long repoId) {
        Repository repository=repositoryTab.findByRepoId(repoId);
        if(repository==null){
            return null;
        }
        else {
            boolean flag=false;
            try {
                repositoryTab.UpdateRepository( repoState,  repoItem,  repoNum,  repoUpNum,  repoFee, repoName, repoId);
                flag=true;
            }
            catch (Exception e){
                System.out.println(e);
            }
            if(flag){return true;}
            else {return false;}
        }
    }
    @Override
    public Boolean UpdateRepositoryNum(Integer repoNum,Long repoId){
        Repository repository=repositoryTab.findByRepoId(repoId);
        if(repository==null){
            return null;
        }
        else {
            boolean flag=false;
            try {
                repositoryTab.UpdateRepositoryNum(repoNum, repoId);
                flag=true;
            }
            catch (Exception e){
                System.out.println(e);
            }
            if(flag){return true;}
            else {return false;}
        }
    }
    
}
