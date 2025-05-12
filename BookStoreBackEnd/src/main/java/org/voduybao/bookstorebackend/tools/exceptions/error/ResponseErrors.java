package org.voduybao.bookstorebackend.tools.exceptions.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ResponseErrors {

    ROLE_NOT_FOUND(ErrorCode.USER_NOT_FOUND, "Role Not Found", HttpStatus.BAD_REQUEST),
    USER_ID_IS_EMPTY(ErrorCode.USER_NOT_FOUND, "User ID is empty", HttpStatus.BAD_REQUEST),
    PLATFORM_ID_IS_EMPTY_OR_0(ErrorCode.PLATFORM_ID_IS_EMPTY_OR_0, "Platform ID is empty or zero", HttpStatus.BAD_REQUEST),
    BAD_REQUEST(ErrorCode.BAD_REQUEST, "Bad request", HttpStatus.BAD_REQUEST),
    AUTH_INVALID(ErrorCode.AUTH_INVALID, "Auth invalid", HttpStatus.BAD_REQUEST),
    INVALID_EMAIL_FORMAT(ErrorCode.INVALID_EMAIL_FORMAT, "Invalid email format", HttpStatus.BAD_REQUEST),
    INVALID_PHONE_NUMBER_FORMAT(ErrorCode.INVALID_PHONE_NUMBER_FORMAT, "Invalid phone number format", HttpStatus.BAD_REQUEST),
    INVALID_USERNAME_FORMAT(ErrorCode.INVALID_USERNAME_FORMAT, "Invalid username format", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD_FORMAT(ErrorCode.AUTH_INVALID, "Invalid password format", HttpStatus.BAD_REQUEST),
    INVALID_REGISTRATION(ErrorCode.INVALID_REGISTRATION, "Invalid registration", HttpStatus.BAD_REQUEST),
    UNSUPPORTED_MEDIA_TYPE(ErrorCode.UNSUPPORTED_MEDIA_TYPE, "Unsupported media type", HttpStatus.BAD_REQUEST),
    PASSWORD_SAME(ErrorCode.PASSWORD_SAME, "Some password", HttpStatus.BAD_REQUEST),
    PASSWORD_INCORRECT(ErrorCode.PASSWORD_INCORRECT, "Password incorrect", HttpStatus.BAD_REQUEST),

    EMAIL_VERIFIED(ErrorCode.EMAIL_VERIFIED, "Email is verified", HttpStatus.BAD_REQUEST),
    PHONE_VERIFIED(ErrorCode.PHONE_VERIFIED, "Phone number is verified", HttpStatus.BAD_REQUEST),

    INVALID_FORMAT_DATE(ErrorCode.INVALID_FORMAT_DATE, "Date must format yyyy-MM-dd", HttpStatus.BAD_REQUEST),
    INVALID_FORMAT_DATE_AND_TIME(ErrorCode.INVALID_FORMAT_DATE, "Date must format yyyy-MM-dd HH:mm:ss", HttpStatus.BAD_REQUEST),

    NOT_SUPPORT_AUTH_TYPE(ErrorCode.NOT_SUPPORT_AUTH_TYPE, "Not support auth type", HttpStatus.BAD_REQUEST),

    WEAK_PASSWORD(ErrorCode.WEAK_PASSWORD, "Weak password", HttpStatus.BAD_REQUEST),

    PUBLISHER_MATCHED_BOOK(ErrorCode.BAD_REQUEST, "Can not delete publisher matched book", HttpStatus.BAD_REQUEST),
    CATEGORY_HAVE_CHILD(ErrorCode.BAD_REQUEST, "Can not delete category have child", HttpStatus.BAD_REQUEST),
    CATEGORY_NAME_SPECIAL_CHAR(ErrorCode.BAD_REQUEST, "Category name have special char", HttpStatus.BAD_REQUEST),
    CATEGORY_NAME_DUPLICATE(ErrorCode.DUPLICATE_NAME_REQUEST, "Category name exist", HttpStatus.BAD_REQUEST),
    CATEGORY_ROOT(ErrorCode.UPDATE_ROOT_REQUEST, "Can not update or delete root", HttpStatus.BAD_REQUEST),
    AUTHOR_NAME_DUPLICATE(ErrorCode.DUPLICATE_NAME_REQUEST, "AUTHOR name exist", HttpStatus.BAD_REQUEST),
    AUTHOR_NAME_IS_SPACE(ErrorCode.VALID_NAME_REQUEST, "AUTHOR name is space", HttpStatus.BAD_REQUEST),
    TITLE_BOOK_REQUIRED(ErrorCode.VALID_NAME_REQUEST, "Title book is required", HttpStatus.BAD_REQUEST),
    TITLE_OPTION_GROUP_REQUIRED(ErrorCode.VALID_NAME_REQUEST, "Title option group is required", HttpStatus.BAD_REQUEST),
    TITLE_OPTION_REQUIRED(ErrorCode.VALID_NAME_REQUEST, "Title option is required", HttpStatus.BAD_REQUEST),
    TITLE_OPTION_GROUP_VALID(ErrorCode.VALID_NAME_REQUEST, "Title option group is valid", HttpStatus.BAD_REQUEST),
    TITLE_OPTION_VALID(ErrorCode.VALID_NAME_REQUEST, "Title option is valid", HttpStatus.BAD_REQUEST),

    AUTHOR_NAME_NO_SPECIAL_CHAR(ErrorCode.BAD_REQUEST, "AUTHOR name have special char", HttpStatus.BAD_REQUEST),
    AUTHOR_HAVE_BOOK_MAPPING(ErrorCode.CANNOT_DELETE_AUTHOR_HAVE_BOOK, "AUTHOR have book mapping cannot delete", HttpStatus.BAD_REQUEST),

    REFRESH_TOKEN_INVALID(ErrorCode.REFRESH_TOKEN_INVALID, "Refresh token invalid", HttpStatus.UNAUTHORIZED),
    TOKEN_INVALID(ErrorCode.TOKEN_INVALID, "Token invalid", HttpStatus.UNAUTHORIZED),
    TOKEN_REVOKED(ErrorCode.TOKEN_REVOKED, "Token revoked", HttpStatus.UNAUTHORIZED),
    TOKEN_EXPIRED(ErrorCode.TOKEN_EXPIRED, "Token expired", HttpStatus.UNAUTHORIZED),

    VERIFICATION_CODE_NOT_FOUND(ErrorCode.VERIFICATION_CODE_NOT_FOUND, "Verify code not found", HttpStatus.UNAUTHORIZED),
    VERIFICATION_CODE_EXPIRED(ErrorCode.VERIFICATION_CODE_EXPIRED, "Verify code expired", HttpStatus.UNAUTHORIZED),
    INVALID_VERIFICATION_CODE(ErrorCode.INVALID_VERIFICATION_CODE, "Invalid verify code", HttpStatus.UNAUTHORIZED),

    ACCESS_DENIED(ErrorCode.ACCESS_DENIED, "Access denied", HttpStatus.FORBIDDEN),
    USER_IS_DEACTIVATED(ErrorCode.USER_IS_DEACTIVATED, "User is deactivated", HttpStatus.FORBIDDEN),
    NOT_AUTHORIZED_ACTION(ErrorCode.NOT_AUTHORIZED_ACTION, "Not authorized to perform this action", HttpStatus.FORBIDDEN),

    MEDIA_GALLERY_NOT_FOUND(ErrorCode.MEDIA_GALLERY_NOT_FOUND, "Media gallery not found", HttpStatus.NOT_FOUND),
    CONVERSATION_NOT_FOUND(ErrorCode.CONVERSATION_NOT_FOUND, "Conversation not found", HttpStatus.NOT_FOUND),
    USER_NOT_FOUND(ErrorCode.USER_NOT_FOUND, "User not found", HttpStatus.NOT_FOUND),

    NOT_FOUND_ID_CATEGORY(ErrorCode.CATEGORY_ID_NOT_FOUND, "Not found category id", HttpStatus.NOT_FOUND),
    NOT_FOUND_PARENT_ID_CATEGORY(ErrorCode.CATEGORY_ID_NOT_FOUND, "Not found parent category id", HttpStatus.NOT_FOUND),

    NOT_FOUND_ID_QUOTE(ErrorCode.QUOTE_ID_NOT_FOUND, "Not found quote id", HttpStatus.NOT_FOUND),

    NOT_FOUND_ID_BOOK(ErrorCode.BOOK_ID_NOT_FOUND, "Not found Book id", HttpStatus.NOT_FOUND),
    NOT_FOUND_ID_PRODUCT(ErrorCode.PRODUCT_ID_NOT_FOUND, "Not found id", HttpStatus.NOT_FOUND),

    NOT_FOUND_ID_BOOK_BUNDLE(ErrorCode.BUNDLE_ID_NOT_FOUND, "Not found PROFILE MEDIA ID", HttpStatus.NOT_FOUND),

    NOT_FOUND_ATTRIBUTE_ID(ErrorCode.ID_NOT_FOUND, "Not found ATTRIBUTE ID", HttpStatus.NOT_FOUND),
    NOT_FOUND_LANGUAGE_ID(ErrorCode.ID_LANGUAGE_NOT_FOUND, "Not found LANGUAGE ID", HttpStatus.NOT_FOUND),
    NOT_FOUND_TYPE_PRODUCT(ErrorCode.TYPE_PRODUCT_NOT_FOUND, "Not found type product", HttpStatus.NOT_FOUND),
    NOT_FOUND_ID_PROFILE(ErrorCode.INVALID_EMAIL_FORMAT, "Not found PROFILE MEDIA ID", HttpStatus.NOT_FOUND),
    NOT_FOUND_ID_AUTHOR(ErrorCode.AUTHOR_ID_NOT_FOUND, "Not found AUTHOR ID", HttpStatus.NOT_FOUND),
    NOT_FOUND_ID_MEDIA(ErrorCode.MEDIA_ID_NOT_FOUND, "Not found MEDIA ID", HttpStatus.NOT_FOUND),
    NOT_FOUND_ID_PUBLISHER(ErrorCode.PUBLISHER_ID_NOT_FOUND, "Not found PUBLISHER ID", HttpStatus.NOT_FOUND),
    NOT_FOUND_ID_MADE_IN(ErrorCode.MADE_IN_ID_NOT_FOUND, "Not found MADE_IN ID", HttpStatus.NOT_FOUND),
    NOT_FOUND_ID_MIND_MAP(ErrorCode.MEDIA_ID_NOT_FOUND, "Not found OPTION_GROUP_ID", HttpStatus.NOT_FOUND),
    NOT_FOUND_ID_OPTION_GROUP(ErrorCode.OPTION_GROUP_ID_NOT_FOUND, "Not found product ID_OPTION_GROUP", HttpStatus.NOT_FOUND),
    NOT_FOUND_ID_OPTION(ErrorCode.OPTION_ID_NOT_FOUND, "Not found OPTION_ID", HttpStatus.NOT_FOUND),

    NOT_FOUND_ID_SHIPPING_ADDRESS(ErrorCode.NOT_FOUND_ID_SHIPPING_ADDRESS, "Not found shipping address", HttpStatus.NOT_FOUND),

    ACCOUNT_EXISTS(ErrorCode.ACCOUNT_EXISTS, "Account exists", HttpStatus.CONFLICT),
    ACCOUNT_EXISTS_WAIT_FOR_VERIFYING(ErrorCode.ACCOUNT_EXISTS_WAIT_FOR_VERIFYING, "Account exists wait for verifying", HttpStatus.CONFLICT),
    SEND_MANY_OTP(ErrorCode.SEND_MANY_OTP, "Send too many OTP requests", HttpStatus.CONFLICT),

    INTERNAL_SERVER_ERROR(ErrorCode.INTERNAL_SERVER_ERROR, "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR),
    UNKNOWN_ERROR(ErrorCode.INTERNAL_SERVER_ERROR, "Server unknown error", HttpStatus.INTERNAL_SERVER_ERROR),
    NOT_FOUND_ID_STATIONERY(ErrorCode.STATIONERY_ID_NOT_FOUND, "Not found Id stationeries", HttpStatus.NOT_FOUND),
    NOT_FOUND_ID_TRADEMARK(ErrorCode.NOT_FOUND_ID_TRADEMARK, "Not found Id trademarks", HttpStatus.NOT_FOUND),
    NOT_FOUND_ID_REVIEW_AREA(ErrorCode.NOT_FOUND_ID_REVIEW_AREA, "Not found Id review_area", HttpStatus.NOT_FOUND),
    NOT_FOUND_ID_REVIEW(ErrorCode.NOT_FOUND_ID_REVIEW, "Not found Id review", HttpStatus.NOT_FOUND);

    private final ErrorCode code;
    private final String message;
    private final HttpStatus status;

    ResponseErrors(ErrorCode code, String message, HttpStatus status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

}
