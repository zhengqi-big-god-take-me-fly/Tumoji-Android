package com.tumoji.tumoji.account.contract;

import com.tumoji.tumoji.common.BaseView;

/**
 * Author: perqin
 * Date  : 12/14/16
 */

public interface SignInSignUpContract {
    interface Presenter {
        /**
         * Init the activity. Called when the activity is created.
         */
        void init();

        /**
         * Called when clicking Next on SignInSignUp progress.
         * @param usernameOrEmail Username or email input by user
         */
        void nextAfterSignInSignUp(String usernameOrEmail);

        /**
         * Called when clicking Next on SignIn progress.
         * @param password Password provided by user.
         */
        void nextAfterSignIn(String password);

        /**
         * Called when clicking Next on SignUp progress.
         * @param username The user desired username.
         * @param email The user desired email.
         * @param password The user desired password.
         * @param passwordConfirm The password confirm field.
         */
        void nextAfterSignUp(String username, String email, String password, String passwordConfirm);

        /**
         * Called when user click the back FAB attached on the Appbar.
         */
        void backToPreviousProgress();

        /**
         * Called when user tap the Close button at the top left corner of the page.
         */
        void stopSignInSignUp();
    }

    interface View extends BaseView<Presenter> {
        /**
         * Go to the SignInSignUp progress
         */
        void pushSignInSignUpProgress();

        /**
         * Go to SignIn progress
         */
        void pushSignInProgress(String usernameOrEmail);

        /**
         * Go to SignUp progress
         * @param username Pre-filled Username field
         * @param email Pre-filled Email field
         */
        void pushSignUpProgress(String username, String email);

        /**
         * Back to previous progress
         */
        void popProgress();

        /**
         * Turn current progress to loading state, showing loading indicator and disabling all
         * user input interface
         */
        void progressStartLoading();

        /**
         * Turn current progress to non-loading state, hiding loading indicator and enabling all
         * user input interface
         */
        void progressStopLoading();

        /**
         * Notice user  that the UsernameOrEmail field is either blank or invalid
         */
        void showUsernameOrEmailBlankOrInvalidError();

        /**
         * Notice user that the Username field is either blank or invalid
         */
        void showUsernameBlankOrInvalidError();

        /**
         * Notice user that the Email field is either blank or invalid
         */
        void showEmailBlankOrInvalidError();

        /**
         * Notice user that the network connection is unavailable
         */
        void showNetworkError();

        /**
         * Notice user that the Password field is either blank or invalid.
         */
        void showPasswordBlankOrInvalidError();

        /**
         * Notice user that the password is wrong and SignIn is failed
         */
        void showPasswordWrongError();

        /**
         * Notice user that the ConfirmPassword field is not the same as the Password field.
         */
        void showPasswordUnconfirmedError();

        /**
         * Finish SignIn
         */
        void finishSignIn();

        /**
         * Finish SignUp
         */
        void finishSignUp();

        /**
         * Cancel SignInSignUp and close the page
         */
        void cancelSignInSignUp();
    }
}
