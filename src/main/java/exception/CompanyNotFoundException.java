/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 *
 * @author Jamie
 */
public class CompanyNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>PersonNotFoundException</code> without
     * detail message.
     */
    public CompanyNotFoundException() {
    }

    /**
     * Constructs an instance of <code>PersonNotFoundException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public CompanyNotFoundException(String msg) {
        super(msg);
    }
}
