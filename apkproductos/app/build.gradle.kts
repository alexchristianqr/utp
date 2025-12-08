plugins {
	alias(libs.plugins.android.application)
	alias(libs.plugins.kotlin.android)
}

android {
	namespace = "com.app.apkproductos"
	compileSdk = 36
	
	defaultConfig {
		applicationId = "com.app.apkproductos"
		minSdk = 24
		targetSdk = 36
		versionCode = 1
		versionName = "1.0"
		
		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
	}
	
	buildTypes {
		release {
			isMinifyEnabled = false
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro"
			)
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_11
		targetCompatibility = JavaVersion.VERSION_11
	}
	kotlinOptions {
		jvmTarget = "11"
	}
	buildFeatures {
		viewBinding = true
	}
}

dependencies {
	
	// Dependencias para Retrofit
	implementation("com.squareup.retrofit2:retrofit:2.9.0")
	implementation("com.squareup.retrofit2:converter-gson:2.9.0")
	implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")
	implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.9.4")


		// Glide para cargar imágenes
	implementation ("com.github.bumptech.glide:glide:4.16.0")




	// 	implementation(libs.androidx.recyclerview)
	//	implementation(libs.androidx.cardview)
	implementation("androidx.recyclerview:recyclerview:1.4.0")
	implementation("androidx.cardview:cardview:1.0.0")
	
	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.appcompat)
	implementation(libs.material)
	implementation(libs.androidx.activity)
	implementation(libs.androidx.constraintlayout)
	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
}