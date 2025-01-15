package br.com.dnsistemas.jetpackcomposetwitterclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.dnsistemas.jetpackcomposetwitterclone.ui.theme.JetpackComposeTwitterCloneTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeTwitterCloneTheme {
                Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 16.dp)
                        .background(Color(0xFF161D26))
                ) {
                    TwitterCard()
                    TuitDivider()
                    TwitterCard()
                }
            }
        }
    }
}

@Composable
fun TwitterCard() {
    var chat by remember { mutableStateOf(false) }
    var rt by remember { mutableStateOf(false) }
    var like by remember { mutableStateOf(false) }
    Row(
        Modifier
            .fillMaxWidth()
            .background(Color(0xFF161D26))
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.profile),
            contentDescription = "Profile",
            modifier = Modifier
                .clip(
                    CircleShape
                )
                .size(55.dp)
        )
        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(Modifier.fillMaxWidth()) {
                TextTitle("Josemberg", Modifier.padding(end = 8.dp))
                DefaultText("@josemberg", Modifier.padding(end = 8.dp))
                DefaultText("5h", Modifier.padding(end = 8.dp))
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    painterResource(R.drawable.ic_dots),
                    contentDescription = "",
                    tint = Color.White
                )
            }
            TextBody(
                "Esta é uma frase grande aiaiaiai" +
                        "Esta é uma frase grande aiaiaiai" +
                        "Esta é uma frase grande aiaiaiai" +
                        "Esta é uma frase grande aiaiaiai" +
                        "Esta é uma frase grande aiaiaiai" +
                        "Esta é uma frase grande aiaiaiai",
                Modifier.padding(8.dp)
            )
            Image(
                painter = painterResource(R.drawable.profile),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(
                        RoundedCornerShape(10.dp)
                    ),
                contentScale = ContentScale.Crop
            )

            Row(Modifier.padding(top = 16.dp)) {
                SocialIcon(
                    modifier = Modifier.weight(1f),
                    unselectedIcon = {
                        Icon(
                            painter = painterResource(
                                R.drawable.ic_chat
                            ),
                            contentDescription = "Unselected Icon",
                            tint = Color(0xFF7E8B98)
                        )
                    },
                    selectedIcon = {
                        Icon(
                            painter = painterResource(
                                R.drawable.ic_chat_filled
                            ),
                            contentDescription = "Selected Icon",
                            tint = Color(0xFF7E8B98)
                        )
                    },
                    isSelected = chat
                ) {
                    chat = !chat
                }

                SocialIcon(
                    modifier = Modifier.weight(1f),
                    unselectedIcon = {
                        Icon(
                            painter = painterResource(
                                R.drawable.ic_rt
                            ),
                            contentDescription = "Unselected Icon",
                            tint = Color(0xFF7E8B98)
                        )
                    },
                    selectedIcon = {
                        Icon(
                            painter = painterResource(
                                R.drawable.ic_rt
                            ),
                            contentDescription = "Selected Icon",
                            tint = Color(0xFF00FF27)
                        )
                    },
                    isSelected = rt
                ) {
                    rt = !rt
                }

                SocialIcon(
                    modifier = Modifier.weight(1f),
                    unselectedIcon = {
                        Icon(
                            painter = painterResource(
                                R.drawable.ic_like
                            ),
                            contentDescription = "Unselected Icon",
                            tint = Color(0xFF7E8B98)
                        )
                    },
                    selectedIcon = {
                        Icon(
                            painter = painterResource(
                                R.drawable.ic_like_filled
                            ),
                            contentDescription = "Selected Icon",
                            tint = Color(0xFF7E8B98)
                        )
                    },
                    isSelected = like
                ) {
                    like = !like
                }
            }
        }
    }
}

@Composable
fun SocialIcon(
    modifier: Modifier,
    unselectedIcon: @Composable () -> Unit,
    selectedIcon: @Composable () -> Unit,
    isSelected: Boolean,
    onItemSelected: () -> Unit
) {
    val defaultValue = 1
    Row(
        modifier = modifier.clickable { onItemSelected() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isSelected) {
            selectedIcon()
        } else {
            unselectedIcon()
        }

        Text(
            text = if (isSelected) (defaultValue + 1).toString() else defaultValue.toString(),
            color = Color(0xFF7E8B98),
            fontSize = 12.sp,
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}

@Composable
fun TextBody(text: String, modifier: Modifier = Modifier) {
    Text(text = text, color = Color.White, modifier = modifier)
}

@Composable
fun TextTitle(title: String, modifier: Modifier = Modifier) {
    Text(text = title, color = Color.White, fontWeight = FontWeight.ExtraBold, modifier = modifier)
}

@Composable
fun DefaultText(title: String, modifier: Modifier = Modifier) {
    Text(text = title, color = Color.White, fontWeight = FontWeight.Light, modifier = modifier)
}

@Composable
fun TuitDivider() {
    HorizontalDivider(
        Modifier
            .padding(top = 4.dp)
            .height(1.dp)
            .fillMaxWidth(),
        color = Color(0xFF7E8B98)
    )
}