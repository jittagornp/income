/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.exception;

import javax.persistence.RollbackException;
import org.springframework.dao.OptimisticLockingFailureException;

/**
 *
 * @author anonymous
 */
public class ErrorMessage {

    public static String from(Throwable t) {
        if (t instanceof InvalidMailException) {
            return "email ไม่ถูกต้อง";
        }

        if (t instanceof AlreadyExistMailException) {
            return "email นี้ถูกใช้งานแล้ว";
        }

        if (t instanceof UncheckedMailException) {
            return "ไม่สามารถส่ง email ได้";
        }

        if (t instanceof RollbackException) {
            return "ไม่สามารถบันทึกข้อมูลได้";
        }

        if (t instanceof OptimisticLockingFailureException) {
            return "มีผู้ใช้ท่านอื่นได้เปลี่ยนแปลงข้อมูลชุดนี้ไปแล้ว กรณาทำการโหลดข้อมูลและลองบันทึกใหม่ดูอีกครั้ง";
        }

        if (t instanceof UserException) {
            return "ผู้ใช้ไม่ดำเนินการตามขั้นตอนที่ระบบกำหนดไว้";
        }

        return "มีข้อผิดพลาดเกิดขึ้นในระหว่างการประมวลผล";
    }
}
