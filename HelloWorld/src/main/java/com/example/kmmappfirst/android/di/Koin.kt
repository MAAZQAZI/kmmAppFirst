import ai.vyro.async.entities.annotations.InternalVoiceAiApi
import androidx.lifecycle.viewmodel.compose.viewModel

import com.example.kmmappfirst.android.ui.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module


val repoModule = module {
   single {  }
}

@OptIn(InternalVoiceAiApi::class)
val viewModelModule = module {

   viewModelOf(::WeatherViewModel)
}


//
//val sharedModule = module {
//    single { WeatherRepository() }
//    single { WeatherUseCase(get()) }
//    viewModel { WeatherViewModel(get()) }
//}