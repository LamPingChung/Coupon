package hk.edu.hkbu.comp.comp4097.coupon.ui.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun Login(nav: NavHostController) {
    val focusRequester = remember {
        FocusRequester()
    }
    val usernameSate = remember { mutableStateOf("") }
    val passwordSate = remember { mutableStateOf("") }
    val isVisibility = remember {
        mutableStateOf(false)
    }


    Column(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().testTag("user"),
            value = usernameSate.value,
            onValueChange = {
                usernameSate.value = it
            },
            label = { Text("Username") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),

            keyboardActions = KeyboardActions(
                onNext = {
                    focusRequester.requestFocus()
                }
            )
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().focusRequester(focusRequester).testTag("password"),
            value = passwordSate.value,
            onValueChange = { passwordSate.value = it },
            label = { Text("Password") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            visualTransformation = if (isVisibility.value) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            }
        )
        Button(
            modifier = Modifier.padding(top = 16.dp).fillMaxWidth(),
            enabled = usernameSate.value.isNotEmpty() && passwordSate.value.isNotEmpty(),
            onClick = { /*TODO*/ }) {
            Text("Login")
        }
    }
}