package com.example.firstcomposeproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val contact = Contact(
            name = "Евгений",
            surname = "Андреевич",
            familyName = "Лукашин",
            imageRes = null,
            isFavorite = true,
            phone = "+7 924 324 32 32",
            address = "г. Москва, 3-я улица Строителей, д. 25, кв. 12",
            email = "ELukashin@practicum.org",
        )

        setContent {
            ContactDetails(contact)
        }
    }
}

@Composable
fun ContactDetails(contact: Contact) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            ContactPhoto(
                contact = contact,
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }

        Text(
            text = listOfNotNull(contact.name, contact.surname).joinToString(" "),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Row(
            modifier = Modifier.padding(4.dp)
        ) {
            Text(
                text = contact.familyName,
                fontSize = 20.sp
            )

            if (contact.isFavorite) Image(
                modifier = Modifier.padding(start = 8.dp).align(Alignment.CenterVertically),
                painter = painterResource(id = android.R.drawable.star_big_on),
                contentDescription = null
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 40.dp)
        ) {
            InfoRow(label = stringResource(R.string.phone), value = contact.phone)
            InfoRow(label = stringResource(R.string.address), value = contact.address)
            if (contact.email != null) {
                InfoRow(label = stringResource(R.string.email), value = contact.email)
            }
        }
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$label:",
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp),
            textAlign = TextAlign.End,
            fontStyle = FontStyle.Italic
        )
        Text(
            text = value,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Start
        )
    }
}

@Composable
fun ContactPhoto(contact: Contact, modifier: Modifier = Modifier) {
    if (contact.imageRes == null) {
        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.circle),
                contentDescription = null,
                tint = Color.LightGray,
                modifier = Modifier.size(65.dp)
            )
            Text(
                text = contact.name.take(1) + contact.familyName.take(1),
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }
    } else {
        Image(
            painter = painterResource(id = contact.imageRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.padding(vertical = 16.dp)
        )
    }
}

@Preview(device = "spec:width=411dp,height=891dp", showSystemUi = true)
@Composable
fun ContactDetailsWithoutImagePreview() {
    val contactWithoutImage = Contact(
        name = "Евгений",
        surname = "Андреевич",
        familyName = "Лукашин",
        imageRes = null,
        isFavorite = true,
        phone = "+7 924 324 32 32",
        address = "г. Москва, 3-я улица Строителей, д. 25, кв. 12",
        email = "ELukashin@practicum.org",
    )

    ContactDetails(contactWithoutImage)
}

@Preview(device = "spec:width=411dp,height=891dp", showSystemUi = true)
@Composable
fun ContactDetailsWithImagePreview() {

    val contactWithImage = Contact(
        name = "Василий",
        familyName = "Кузякин",
        imageRes = R.drawable.nia_480,
        isFavorite = false,
        phone = "---",
        address = "Ивановская область, дер. Крутово, д. 4"
    )

    ContactDetails(contactWithImage)
}
