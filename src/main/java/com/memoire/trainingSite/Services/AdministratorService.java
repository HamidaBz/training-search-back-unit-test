package com.memoire.trainingSite.Services;

import com.memoire.trainingSite.DAO.SiteUserRepo;

public class AdministratorService extends SiteUserService{
    public AdministratorService(SiteUserRepo siteUserRepo) {
        super(siteUserRepo);
    }
}
