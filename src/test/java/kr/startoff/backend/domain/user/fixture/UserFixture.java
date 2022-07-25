package kr.startoff.backend.domain.user.fixture;

import java.util.Collections;
import java.util.UUID;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.util.ReflectionTestUtils;

import kr.startoff.backend.domain.user.domain.AuthProvider;
import kr.startoff.backend.domain.user.domain.Profile;
import kr.startoff.backend.domain.user.domain.User;
import kr.startoff.backend.domain.user.domain.security.UserPrincipal;
import kr.startoff.backend.domain.user.dto.request.LoginRequest;
import kr.startoff.backend.domain.user.dto.request.LogoutRequest;
import kr.startoff.backend.domain.user.dto.request.NicknameRequest;
import kr.startoff.backend.domain.user.dto.request.ProfileRequest;
import kr.startoff.backend.domain.user.dto.request.RefreshRequest;
import kr.startoff.backend.domain.user.dto.request.SignupRequest;
import kr.startoff.backend.domain.user.dto.request.UserPasswordChangeRequest;
import kr.startoff.backend.domain.user.dto.response.AccessTokenResponse;
import kr.startoff.backend.domain.user.dto.response.LoginResponse;
import kr.startoff.backend.domain.user.dto.response.UserInfoResponse;
import kr.startoff.backend.domain.user.dto.response.UserProfileResponse;

public class UserFixture {
	public static final Long USER_ID = 1L;
	public static final String EMAIL = "proto_seo@naver.com";
	public static final String PASSWORD = "password";
	public static final String NEW_PASSWORD = "newPassword";
	public static final String NICKNAME = "nickname";
	public static final String BAEKJOON_ID = "proto_type";
	public static final String GITHUB_URL = "https://github.com/protoseo";
	public static final String BLOG_URL = "https://newBlogUrl.blog.com";
	public static final String INTRODUCE = "Introduce";
	public static final String USER_UUID = UUID.randomUUID().toString();
	public static final String ACCESS_TOKEN = "accessToken";
	public static final String IMAGE_URL = "";

	public static User user() {
		User user = User.builder()
			.email(EMAIL)
			.nickname(NICKNAME)
			.password(PASSWORD)
			.provider(AuthProvider.LOCAL)
			.build();
		ReflectionTestUtils.setField(user, "id", USER_ID);
		return user;
	}

	public static UserPrincipal userPrincipal() {
		return new UserPrincipal(USER_ID, EMAIL, NICKNAME, PASSWORD, Collections.
			singletonList(new SimpleGrantedAuthority("ROLE_USER")));
	}

	public static Profile profile() {
		return Profile.builder()
			.githubUrl(GITHUB_URL)
			.blogUrl(BLOG_URL)
			.baekjoonId(BAEKJOON_ID)
			.introduce(INTRODUCE)
			.build();
	}

	public static SignupRequest signupRequest() {
		return new SignupRequest(EMAIL, NICKNAME, PASSWORD);
	}

	public static LoginRequest loginRequest() {
		return new LoginRequest(EMAIL, PASSWORD);
	}

	public static LogoutRequest logoutRequest() {
		return new LogoutRequest(USER_UUID, ACCESS_TOKEN);
	}

	public static NicknameRequest nicknameRequest() {
		return new NicknameRequest(NICKNAME);
	}

	public static ProfileRequest profileRequest() {
		return new ProfileRequest(GITHUB_URL, BLOG_URL, BAEKJOON_ID, INTRODUCE);
	}

	public static RefreshRequest refreshRequest() {
		return new RefreshRequest(USER_UUID, EMAIL, ACCESS_TOKEN);
	}

	public static UserPasswordChangeRequest userPasswordChangeRequest() {
		return new UserPasswordChangeRequest(PASSWORD, NEW_PASSWORD);
	}

	public static AccessTokenResponse accessTokenResponse() {
		return AccessTokenResponse.of(USER_ID, ACCESS_TOKEN);
	}

	public static LoginResponse loginResponse() {
		return LoginResponse.of(userPrincipal(), ACCESS_TOKEN, USER_UUID);
	}

	public static UserInfoResponse userInfoResponse() {
		return UserInfoResponse.from(user());
	}

	public static UserProfileResponse userProfileResponse() {
		return UserProfileResponse.from(profile());
	}
}
