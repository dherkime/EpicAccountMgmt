package epicaccountmgmt

class Acct {
    String name;
    Date dob;
    String email;

    String toString() {
        return this.name + "; " + email;
    }

    static constraints = {
        name blank: false
        dob blank: false
        email email: true, blank: false,  unique: true
    }
}
