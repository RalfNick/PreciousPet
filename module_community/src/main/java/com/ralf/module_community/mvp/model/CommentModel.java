package com.ralf.module_community.mvp.model;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.ralf.module_community.mvp.contact.CommentContact;

import javax.inject.Inject;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name CommentModel
 * @email -
 * @date 2019/01/15 上午10:04
 **/
@ActivityScope
public class CommentModel extends BaseModel implements CommentContact.Model {

    @Inject
    public CommentModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

}
