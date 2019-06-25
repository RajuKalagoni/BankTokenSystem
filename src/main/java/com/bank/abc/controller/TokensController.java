package com.bank.abc.controller;

import com.bank.abc.model.Customer;
import com.bank.abc.model.Token;
import com.bank.abc.service.TokenService;
import com.bank.abc.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/xyz")
public class TokensController {

    @Autowired
    TokenService tokenService;


    @RequestMapping(value = "/tokens", method = RequestMethod.GET)
    public ResponseEntity list() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(tokenService.list());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
        }
    }

    @RequestMapping(value = "/token/waitingCustomers", method = RequestMethod.GET)
    public ResponseEntity getUnassignedTokens() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(tokenService.getUnassignedTokens());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
        }
    }

    @RequestMapping(value = "/token/{id}", method = RequestMethod.GET)
    public ResponseEntity get(@PathVariable Long id) {
        try {
            Token token = tokenService.getTokenById(id);
            if (ObjectUtils.isEmpty(token)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token not found with id:" + id);
            }
            return ResponseEntity.status(HttpStatus.OK).body(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
        }
    }

    @RequestMapping(value = "/tokens/{id}/type", method = RequestMethod.GET)
    public ResponseEntity getTokenPriorityType(@PathVariable Long id) {
        try {
            PriorityType priorityType = tokenService.getTokenPriorityType(id);
            if (ObjectUtils.isEmpty(priorityType))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token not found with id:" + id);

            return ResponseEntity.status(HttpStatus.OK).body(priorityType);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
        }
    }

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody Token token) {
        try {
            Customer customer = CustomerStub.getCustomerById(token.getCustId());
            if (ObjectUtils.isEmpty(customer))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Customer Id, Please check");

            return ResponseEntity.status(HttpStatus.CREATED).body(tokenService.createToken(token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
        }
    }

    @RequestMapping(value = "/tokens/{id}", method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable Long id, @RequestBody Token token) {
        try {

            if (tokenService.getAllTokens().get(token.getId()) == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token not found with id:" + id);

            if (!TokenUtils.isValidTokenToServe(TokenStub.getQueues(), token)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token with id:" + id + " is not valid one to serve");
            }

            if (!(token.getStatus() instanceof Status))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token Status should be OPEN/COMPLETED/CANCELLED/FORWARDED. Found invalid Status, Please check.");

            Token updatedToken = tokenService.updateToken(id, token);

            return ResponseEntity.status(HttpStatus.OK).body(updatedToken);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
        }
    }

    @RequestMapping(value = "/tokens/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            Token token = tokenService.deleteToken(id);
            if (ObjectUtils.isEmpty(token))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token not found with id:" + id);

            return ResponseEntity.status(HttpStatus.OK).body(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
        }
    }
}
