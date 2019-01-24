# Documentation

## Configuration de l’interface de paiement

Avant de pouvoir faire fonctionner les paiements, vous devez spécifier les paramètres de votre compte marchand (clé API, site ID, URL de notification) et définir une interface entre votre application et CinetPay. Cette interface permet de communiquer à CinetPay les informations de chaque paiement (montant, devise, ID de la transaction...) qu’un utilisateur s’apprête à effectuer.

Pour ce faire, créez une classe qui hérite de la classe `CinetPayWebAppInterface`.

Un exemple ci-dessous:

```java
public class MyCinetPayWebAppInterface extends CinetPayWebAppInterface {

	public MyCinetPayWebAppInterface(Context c, String api_key, int site_id, String notify_url, String trans_id, int amount, String currency, String designation, String custom, boolean should_check_payment) {
		super(c, api_key, site_id, notify_url, trans_id, amount, currency, designation, custom, boolean should_check_payment);
	}

	@Override
	@JavascriptInterface
	public void onPaymentCompleted(String payment_info) {
	}

	@Override
	@JavascriptInterface
	public void onError(String code, String message) {
	}

	@Override
    @JavascriptInterface
    public void terminatePending(String apikey, int cpm_site_id, String cpm_trans_id) {
    }

    @Override
    @JavascriptInterface
    public void terminateSuccess(String payment_info) {
    }

    @Override
    @JavascriptInterface
    public void terminateFailed(String apikey, int cpm_site_id, String cpm_trans_id) {
    }

    @Override
    @JavascriptInterface
    public void checkPayment(String apikey, int cpm_site_id, String cpm_trans_id) {
    }
}
```

Cette classe contient des méthodes qui sont déclenchées en fonction de certains événements:

- `onPaymentCompleted`: S'exécute lorsque le paiement est terminé. Elle prend en paramètre une chaîne de caractères au format JSON qui contient toutes les informations concernant le paiement effectué.

La structure de la chaîne se présente ainsi:

```json
{
	"cpm_site_id": "",
	"signature": "", 
	"cpm_amount": "", 
	"cpm_trans_date": "", 
	"cpm_trans_id": "", 
	"cpm_custom": "", 
	"cpm_currency": "", 
	"cpm_payid": "", 
	"cpm_payment_date": "", 
	"cpm_payment_time": "", 
	"cpm_error_message": "", 
	"payment_method": "", 
	"cpm_phone_prefixe": "", 
	"cel_phone_num": "", 
	"cpm_ipn_ack": "", 
	"created_at": "", 
	"updated_at": "", 
	"cpm_result": "", 
	"cpm_trans_status": "", 
	"cpm_designation": "", 
	"buyer_name": ""
}
```

Pour plus d'informations sur la signification de chaque paramètre, consultez le document [Réussir l'intégration CinetPay](https://cinetpay.com/downloads/Reussir_l_integration_CinetPay_v1.6.0.pdf).

Pour récupérer des éléments de cette chaîne, vous devez donc la convertir en un objet JSON comme dans l'exemple ci-dessous:

```java
try {
	JSONObject paymentInfo = new JSONObject(payment_info);

	String cpm_result = paymentInfo.getString("cpm_result");
	String cpm_trans_status = paymentInfo.getString("cpm_trans_status");
	String cpm_error_message = paymentInfo.getString("cpm_error_message");
	String cpm_custom = paymentInfo.getString("cpm_custom");

} catch (JSONException e) { 
	e.printStackTrace(); 
}
```

- `onError`: S'exécute lorsqu'une erreur survient. Elle prend en paramètres le code et le message de l’erreur.

- `terminatePending`: S'exécute lorsque l'utilisateur appuie sur le bouton `Annuler` après avoir initié un paiement sans valider (la validation dont on parle ici est le fait de saisir son code secret sur ton téléphone lors de la dernière étape du paiement, dans le cas de MTN et Moov). Le bouton `Annuler` s'affiche lorsque l'utilisateur clique sur `Fermer` dans la fenêtre de paiement CinetPay. La méthode prend en paramètres: `apikey` (votre clé API), `cpm_site_id` (votre site ID), `cpm_trans_id` (l'ID de transaction que vous avez généré pour le paiement en question). Ces paramètres vous sont transmis pour vous permettre de vérifier le statut final du paiement car l'utilisateur peut confirmer le paiement après avoir quitté la fenêtre de paiement de CinetPay. Pour vérifier le statut final du paiement, vous devez envoyer par POST les paramètres suivants: `apikey`, `cpm_site_id` et `cpm_trans_id` à cette URL: `https://api.cinetpay.com/v1/?method=checkPayStatus`. Pour plus d'informations sur les éléments retournés, consultez le document [Réussir l'intégration CinetPay](https://cinetpay.com/downloads/Reussir_l_integration_CinetPay_v1.6.0.pdf).

- `terminateSuccess`: S'exécute lorsque l'utilisateur appuie sur le bouton `Terminer`. Le bouton `Terminer` s'affiche lorsque l'utilisateur clique sur `Fermer` dans la fenêtre de paiement CinetPay après avoir effectué son paiement. La méthode prend en paramètre une chaîne de caractères au format JSON qui contient toutes les informations concernant le paiement effectué, la même que celle dans `onPaymentCompleted`.

- `terminateFailed`: S'exécute lorsque l'utilisateur appuie sur le bouton `Terminer` après une erreur survenue. Le bouton `Terminer` s'affiche lorsque l'utilisateur clique sur `Fermer` dans la fenêtre de paiement CinetPay. La méthode prend en paramètres: `apikey` (votre clé API), `cpm_site_id` (votre site ID), `cpm_trans_id` (l'ID de transaction que vous avez généré pour le paiement en question). Ces paramètres vous sont transmis pour vous permettre de vérifier le statut final du paiement. Pour vérifier le statut final du paiement, vous devez envoyer par POST les paramètres suivants: `apikey`, `cpm_site_id` et `cpm_trans_id` à cette URL: `https://api.cinetpay.com/v1/?method=checkPayStatus`. Pour plus d'informations sur les éléments retournés, consultez le document [Réussir l'intégration CinetPay](https://cinetpay.com/downloads/Reussir_l_integration_CinetPay_v1.6.0.pdf).

- `checkPayment`: S'exécute lorsque l'utilisateur clique sur le bouton `Vérifier le paiement`. Le bouton `Vérifier le paiement` s'affiche (si vous avez mis le paramètre `should_check_payment` à `true` à l'instanciation de votre classe qui hérite de `CinetPayWebAppInterface`) lorsque l'utilisateur clique sur `Fermer` dans la fenêtre de paiement CinetPay après avoir initié un paiement sans valider (la validation dont on parle ici est le fait de saisir son code secret sur ton téléphone lors de la dernière étape du paiement, dans le cas de MTN et Moov). La méthode prend en paramètres: `apikey` (votre clé API), `cpm_site_id` (votre site ID), `cpm_trans_id` (l'ID de transaction que vous avez généré pour le paiement en question). Pour vérifier le statut final du paiement, vous devez envoyer par POST les paramètres suivants: `apikey`, `cpm_site_id` et `cpm_trans_id` à cette URL: `https://api.cinetpay.com/v1/?method=checkPayStatus`. Pour plus d'informations sur les éléments retournés, consultez le document [Réussir l'intégration CinetPay](https://cinetpay.com/downloads/Reussir_l_integration_CinetPay_v1.6.0.pdf).

Nous verrons plus tard où et comment appeler cette classe.

## Affichage de la page de paiement de CinetPay

Après avoir défini le processus de paiement, vous pouvez maintenant rediriger l’utilisateur vers la page de paiement de CinetPay.

Vous devez d’abord créer une `Activity` qui hérite de `CinetPayActivity`:

```java
public class MyCinetPayActivity extends CinetPayActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
	} 
}
```

Assurez-vous de l’ajouter dans le fichier `AndroidManifest.xml`:

```xml
<activity android:name=".MyCinetPayActivity" />
```

Android Studio le fera automatiquement si vous créez l’`Activity` à partir du menu dédié.

Vous devez appeler cette `Activity` à chaque fois qu’un utilisateur déclenche une action de paiement, tout en envoyant ces paramètres:

- **KEY_API_KEY**: votre clé API
- **KEY_SITE_ID**: votre site ID
- **KEY_NOTIFY_URL**: votre URL de notification. Vous pouvez laisser ce champ vide.
- **KEY_TRANS_ID**: l’ID de la transaction. Vous devez la générer de façon dynamique et elle doit être unique.
- **KEY_AMOUNT**: le montant du paiement.
- **KEY_CURRENCY**: la devise.
- **KEY_DESIGNATION**: la désignation qui représente le libellé du paiement.
- **KEY_CUSTOM**: un contenu personnalisé que vous envoyez à CinetPay qui peut vous servir dans votre gestion. Ce champ peut rester vide.

Exemple:

```java
String api_key = "MY_API_KEY"; // A remplacer par votre clé API 
int site_id = 0; // A remplacer par votre Site ID
String notify_url = "";
String trans_id = String.valueOf(new Date().getTime());
int amount = 100 ;
String currency = "CFA";
String designation = "Achat test"; 
String custom = "";

Intent intent = new Intent(MainActivity.this,MyCinetPayActivity.class); 
intent.putExtra(CinetPayActivity.KEY_API_KEY, api_key); 
intent.putExtra(CinetPayActivity.KEY_SITE_ID, site_id); 
intent.putExtra(CinetPayActivity.KEY_NOTIFY_URL, notify_url); 
intent.putExtra(CinetPayActivity.KEY_TRANS_ID, trans_id); 
intent.putExtra(CinetPayActivity.KEY_AMOUNT, amount); 
intent.putExtra(CinetPayActivity.KEY_CURRENCY, currency); 
intent.putExtra(CinetPayActivity.KEY_DESIGNATION, designation);
intent.putExtra(CinetPayActivity.KEY_CUSTOM, custom);
startActivity(intent);
```

Enfin, vous devez récupérer ces paramètres dans votre `Activity` préalablement créée (`MyCInetPayActivity` ici) et les passer à la classe qui hérite de `CinetPayWebAppInterface` (`MyCinetPayWebAppInterface` ici).

```java
public class MyCinetPayActivity extends CinetPayActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Intent intent = getIntent();

		String api_key = intent.getStringExtra(KEY_API_KEY);
		int site_id = intent.getIntExtra(KEY_SITE_ID, 0);
		String notify_url = intent.getStringExtra(KEY_NOTIFY_URL);
		String trans_id = intent.getStringExtra(KEY_TRANS_ID);
		int amount = intent.getIntExtra(KEY_AMOUNT, 0);
		String currency = intent.getStringExtra(KEY_CURRENCY);
		String designation = intent.getStringExtra(KEY_DESIGNATION);
		String custom = intent.getStringExtra(KEY_CUSTOM);

		mWebView.addJavascriptInterface(new MyCinetPayWebAppInterface(this, api_key, site_id, notify_url, trans_id, amount, currency, designation, custom, true), "Android");
	}
}
```

Le `mWebView` vient de `CinetPayActivity`. Vous y avez accès parce que votre `Activity` hérite de `CinetPayActivity`. Notez que vous ne devez pas définir de layout pour `MyCinetPayActivity`.