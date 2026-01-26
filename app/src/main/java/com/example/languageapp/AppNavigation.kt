package com.example.languageapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.languageapp.comp.presentation.imagepicker.ImagePicker
import com.example.languageapp.comp.presentation.language.LanguageSelectScreen
import com.example.languageapp.comp.presentation.mainScreen.MainScreen
import com.example.languageapp.comp.presentation.onBoarding.OnBoardingRoot
import com.example.languageapp.comp.presentation.profile.ProfileScreen
import com.example.languageapp.comp.presentation.signIn.SignInRoot
import com.example.languageapp.comp.presentation.signUp.SignUpRoot
import com.example.languageapp.comp.presentation.splash.SplashScreen
import com.example.languageapp.comp.presentation.theme.ThemeEvent
import com.example.languageapp.comp.presentation.theme.ThemeViewModel
import com.example.languageapp.ui.theme.LanguageAppTheme

@Composable
fun AppNavigation(
    navController: NavHostController,
    themeViewModel: ThemeViewModel = hiltViewModel()
) {
    val state by themeViewModel.state.collectAsStateWithLifecycle()
    LanguageAppTheme(darkTheme = state.isDark) {
        NavHost(
            navController = navController,
            startDestination = Destinations.SplashScreen
        ) {
            composable<Destinations.SplashScreen> {
                SplashScreen(
                    onLoginScreen = {
                        navController.navigate(Destinations.SignInScreen) {
                            popUpTo(Destinations.SplashScreen) {
                                inclusive = true
                            }
                        }
                    },
                    onMainScreen = {
                        navController.navigate(Destinations.MainScreen) {
                            popUpTo(Destinations.SplashScreen) {
                                inclusive = true
                            }
                        }
                    },
                    onOnBoardingScreen = {
                        navController.navigate(Destinations.OnBoardingScreen) {
                            popUpTo(Destinations.SplashScreen) {
                                inclusive = true
                            }
                        }
                    },
                )
            }
            composable<Destinations.OnBoardingScreen> {
                OnBoardingRoot(
                    onSignIn = {
                        navController.navigate(Destinations.SignInScreen) {
                            popUpTo(Destinations.OnBoardingScreen) {
                                inclusive = true
                            }
                        }
                    },
                    onLanguageSelect = {
                        navController.navigate(Destinations.LanguageSelectScreen(onClick = {
                            navController.navigate(Destinations.SignInScreen)
                        })) {
                            popUpTo(Destinations.OnBoardingScreen) {
                                inclusive = true
                            }
                        }
                    }
                )
            }
            composable<Destinations.SignInScreen> {
                SignInRoot(
                    onSignUpClick = { navController.navigate(Destinations.SignUpScreen1) },
                    onSignInSuccess = {
                        navController.navigate(Destinations.MainScreen) {
                            popUpTo(Destinations.SignInScreen) {
                                inclusive = true
                            }
                        }
                    }
                )
            }
            composable<Destinations.SignUpScreen1> {
                SignUpRoot(
                    onLogInClick = { navController.popBackStack() },
                    onSignUp = {
                        navController.navigate(Destinations.MainScreen) {
                            popUpTo(Destinations.SignUpScreen1) {
                                inclusive = true
                            }
                        }
                    }
                )
            }
            composable<Destinations.MainScreen> {
                MainScreen(
                    onProfileClick = { navController.navigate(Destinations.ProfileScreen) }
                )
            }
            composable<Destinations.ProfileScreen> {
                ProfileScreen(
                    onLogout = {
                        navController.navigate(Destinations.SignInScreen) {
                            popUpTo(Destinations.MainScreen) {
                                inclusive = true
                            }
                        }
                    },
                    onToggleTheme = {
                        themeViewModel.onEvent(ThemeEvent.Toggle)
                    },
                    onImageChange = {
                        navController.navigate(Destinations.ImagePicker)
                    },
                    onLanguageChange = {
                        navController.navigate(Destinations.LanguageSelectScreen(
                            onClick = {
                                navController.navigate(Destinations.ProfileScreen){
                                    popUpTo(Destinations.ProfileScreen){
                                        inclusive = true
                                    }
                                }
                            }
                        ))
                    }
                    )
            }
            composable<Destinations.LanguageSelectScreen> {
                val args = it.toRoute<Destinations.LanguageSelectScreen>()
                LanguageSelectScreen(
                    onSubmit = {
                        args.onClick
                    }
                )
            }
            composable<Destinations.ImagePicker> {
                ImagePicker(
                    onImageSaved = {
                        navController.navigate(Destinations.ProfileScreen){
                            popUpTo(Destinations.MainScreen){
                                inclusive = false
                            }
                        }
                    }
                )
            }
        }
    }
}