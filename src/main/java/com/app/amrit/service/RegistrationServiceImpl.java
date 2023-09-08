package com.app.amrit.service;

import com.app.amrit.constants.AppConstants;

import com.app.amrit.entity.CityEntity;
import com.app.amrit.entity.CountryEntity;
import com.app.amrit.entity.StateEntity;
import com.app.amrit.entity.UserEntity;
import com.app.amrit.exception.RegAppException;
import com.app.amrit.model.User;
import com.app.amrit.props.AppProperties;
import com.app.amrit.repository.CityRepository;
import com.app.amrit.repository.CountryRepository;
import com.app.amrit.repository.StateRepository;
import com.app.amrit.repository.UserRepository;
import com.app.amrit.util.EmailUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Stream;

@Service
public class RegistrationServiceImpl implements RegistrationService{

    @Autowired
    private  UserRepository userRepository;

    @Autowired
    private  CountryRepository countryRepository;

    @Autowired
    private  StateRepository stateRepository;

    @Autowired
    private  CityRepository cityRepository;

    @Autowired
    private EmailUtils emailUtils;

    @Autowired
    private  AppProperties appProps;


    @Override
    public boolean uniqueEmail(String email) {
        UserEntity userEntity = userRepository.findByUserEmail(email);

        if(userEntity != null) {
            return false;
        }
        return true;
    }

    @Override
    public Map<Integer, String> getCountries() {
        List<CountryEntity> allCountries = countryRepository.findAll();

        Map<Integer,String> countryMap = new HashMap<>();

        for(CountryEntity entity:allCountries){
            countryMap.put(entity.getCountryId(), entity.getCountryName());
        }

        return countryMap;
    }

    @Override
    public Map<Integer, String> getStates(Integer countryId) {
        List<StateEntity> stateEntity = stateRepository.findByCountryId(countryId);

        Map<Integer,String> statesMap = new HashMap<>();

        for(StateEntity entity:stateEntity){
            statesMap.put(entity.getStateId(), entity.getStateName());
        }

        return statesMap;
    }

    @Override
    public Map<Integer, String> getCities(Integer stateId) {

        List<CityEntity> cities = cityRepository.findByStateId(stateId);

        Map<Integer,String> citiesMap = new HashMap<>();

        for(CityEntity entity:cities){
            citiesMap.put(entity.getCityId(), entity.getCityName());
        }

        return citiesMap;
    }

    @Override
    public boolean registerUser(User user) {
        user.setUserPwd(generateTempPwd());
        user.setUserAccStatus(AppConstants.LOCKED);

        UserEntity entity = new UserEntity();
        BeanUtils.copyProperties(user,entity);

        UserEntity save = userRepository.save(entity);

        if (null != save.getUserId()) {
            return sendRegEmail(user);
        }

        return false;
    }

    private boolean sendRegEmail(User user) {
        boolean emailSent = false;
        try {
            Map<String, String> messages = appProps.getMessages();
            String subject = messages.get(AppConstants.REG_MAIL_SUBJECT);
            String bodyFileName = messages.get(AppConstants.REG_MAIL_BODY_TEMPLATE_FILE);
            String body = readMailBody(bodyFileName, user);
            emailUtils.sendEmail(subject, body, user.getUserEmail());
            emailSent = true;
        } catch (Exception e) {
            throw new RegAppException(e.getMessage());
        }
        return emailSent;
    }
    public String readMailBody(String fileName, User user) {
        String mailBody = null;
        StringBuffer buffer = new StringBuffer();
        Path path = Paths.get(fileName);
        try (Stream<String> stream = Files.lines(path)) {
            stream.forEach(line -> {
                buffer.append(line);
            });
            mailBody = buffer.toString();
            mailBody = mailBody.replace(AppConstants.FNAME, user.getUserFname());
            mailBody = mailBody.replace(AppConstants.EMAIL, user.getUserEmail());
            mailBody = mailBody.replace(AppConstants.TEMP_PWD, user.getUserPwd());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mailBody;
    }
    private String generateTempPwd(){
        String tempPwd = null;
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 6;
        Random random = new Random();

        tempPwd = random.ints(leftLimit, rightLimit + 1).filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

        return tempPwd;
    }
}
