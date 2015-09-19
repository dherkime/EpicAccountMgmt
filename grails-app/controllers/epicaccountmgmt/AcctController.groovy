package epicaccountmgmt

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class AcctController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Acct.list(params), model:[acctCount: Acct.count()]
    }

    def show(Acct acct) {
        respond acct
    }

    def create() {
        respond new Acct(params)
    }

    @Transactional
    def save(Acct acct) {
        if (acct == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (acct.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond acct.errors, view:'create'
            return
        }

        acct.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'acct.label', default: 'Acct'), acct.id])
                redirect acct
            }
            '*' { respond acct, [status: CREATED] }
        }
    }

    def edit(Acct acct) {
        respond acct
    }

    @Transactional
    def update(Acct acct) {
        if (acct == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (acct.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond acct.errors, view:'edit'
            return
        }

        acct.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'acct.label', default: 'Acct'), acct.id])
                redirect acct
            }
            '*'{ respond acct, [status: OK] }
        }
    }

    @Transactional
    def delete(Acct acct) {

        if (acct == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        acct.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'acct.label', default: 'Acct'), acct.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'acct.label', default: 'Acct'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
