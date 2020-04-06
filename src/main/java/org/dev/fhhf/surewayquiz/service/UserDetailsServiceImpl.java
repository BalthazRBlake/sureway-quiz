package org.dev.fhhf.surewayquiz.service;

import org.dev.fhhf.surewayquiz.model.Agent;
import org.dev.fhhf.surewayquiz.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AgentRepository agentRepo;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<Agent> user = agentRepo.findByName(userName);

        user.orElseThrow(() -> new UsernameNotFoundException("Not Found: " + userName));

        return user.map(UserDetailsImpl::new).get();
    }

}
