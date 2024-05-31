package com.librarymanagementsystem.librarymanagementsystem.serviceImp;

import com.librarymanagementsystem.librarymanagementsystem.entity.Admin;
import com.librarymanagementsystem.librarymanagementsystem.exception.AdminException;
import com.librarymanagementsystem.librarymanagementsystem.repository.AdminRepository;
import com.librarymanagementsystem.librarymanagementsystem.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImp implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

//    public AdminServiceImp(AdminRepository adminRepository) {
//        this.adminRepository = adminRepository;
//    }

    @Override
    public Admin addAdmin(Admin admin) throws AdminException {
        if (adminRepository.findByPhone(admin.getPhone()) != null) {
            throw new AdminException("user details already exists");
        }
        return adminRepository.save(admin);
    }

    @Override
    public Admin getAdmin(int id) throws AdminException {
        return adminRepository.findById(id).orElse(null);
    }

    @Override
    public List<Admin> getAdmins() throws AdminException {
        return adminRepository.findAll();
    }

    @Override
    public Admin updateAdmin(Admin admin) throws AdminException {
        Admin existingAdmin = adminRepository.findById(admin.getAdminId()).orElse(null);

        assert existingAdmin != null;
        existingAdmin.setName(admin.getName());
        existingAdmin.setPhone(admin.getPhone());
        existingAdmin.setPassword(admin.getPassword());
        return adminRepository.save(existingAdmin);
    }

    @Override
    public void deleteAdmin(int id) throws AdminException {
        adminRepository.deleteById(id);
    }

    @Override
    public Admin adminLogin(String name, String password) throws AdminException{
        //Optional<Admin> adminOptional = adminRepository.findByName(name);
        Admin admin = adminRepository.findByName(name);
        //if (adminOptional.isPresent()) {
            //Admin admin = adminOptional.get();
            // Admin admin = adminRepository.findByName(name);
            if (/*admin != null &&*/ admin.getPassword().equals(password)) {
                return admin;
            }
//             else {
//                return null;
//            }
        //}
        return null;
    }
}
