package br.gov.ufg.entity;

public class Login {
    private String email;
    private String senha;

    public Login() {}

    public Login(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public boolean login(String email, String password) {
        return email.equals(this.email) && password.equals(this.senha);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Login [email=" + email + ", senha=" + senha + "]";
    }

    public String toTxt() {
        return
            email + "," +
            senha;
    }
}
