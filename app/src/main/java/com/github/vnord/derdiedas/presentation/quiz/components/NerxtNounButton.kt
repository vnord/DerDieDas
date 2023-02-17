import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.github.vnord.derdiedas.R

@Composable
fun NextNounButton(modifier: Modifier = Modifier, onClick: () -> Unit, enabled: Boolean) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
    ) {
        Icon(
            imageVector = Icons.Default.SkipNext,
            contentDescription = stringResource(R.string.next_noun),
        )
    }
}
