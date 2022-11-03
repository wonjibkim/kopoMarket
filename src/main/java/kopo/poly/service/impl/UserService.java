package kopo.poly.service.impl;


import kopo.poly.persistance.mapper.IUserMapper;
import kopo.poly.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service("UserService")
@Slf4j
@RequiredArgsConstructor
public class UserService implements IUserService {

        private IUserMapper userMapper;





}

