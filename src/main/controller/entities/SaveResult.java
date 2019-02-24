package main.controller.entities;

public class SaveResult {
    private boolean inputError = false;
    private boolean savingError = false;

    public SaveResult() {
    }

    public boolean isSuccess() {
        return !inputError && !savingError;
    }

    public boolean isInputError() {
        return inputError;
    }

    public boolean isSavingError() {
        return savingError;
    }

    public SaveResult setInputError() {
        inputError = true;
        return this;
    }

    public SaveResult setSavingError() {
        savingError = true;
        return this;
    }
}
