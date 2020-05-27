package com.ymy.graduation.repository;

import com.ymy.graduation.domain.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

/**
 * @author ymyum
 * @date 2020/2/4 20:59
 * @project
 */
public interface repositoryTab extends JpaRepository<Repository,Integer> {
    /**查找*/
    @Query(value = "SELECT repository From Repository repository WHERE repository.repoId=?1")
    Repository findByRepoId(Long repoId);
//    @Query(value = "SELECT repository From Repository repository WHERE repository.InStorage.inDate=?1")
//    List<Repository> findByRepoDate(Date inDate);


    /**修改*/
    @Transactional
    @Modifying
    @Query(value = "UPDATE Repository repository SET repository.repoState=?1,repository.repoItem=?2,repository.repoNum=?3,repository.repoUpNum=?4,repository.repoFee=?5,repository.repoName=?6 WHERE repository.repoId=?7")
    void UpdateRepository(String repoState, String repoItem, Integer repoNum, Integer repoUpNum, Double repoFee, String repoName,Long repoId);
    @Transactional
    @Modifying
    @Query(value = "UPDATE Repository repository SET repository.repoNum=?1 WHERE repository.repoId=?2")
    void UpdateRepositoryNum(Integer repoNum, Long repoId);

}
