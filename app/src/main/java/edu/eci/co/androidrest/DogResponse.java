package edu.eci.co.androidrest;

import com.google.gson.annotations.SerializedName;

public class DogResponse {
    @SerializedName("message")
    private String message; // URL de la imagen

    @SerializedName("status")
    private String status;


    public DogResponse(String message, String status) {
        this.message = message;
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }
}
