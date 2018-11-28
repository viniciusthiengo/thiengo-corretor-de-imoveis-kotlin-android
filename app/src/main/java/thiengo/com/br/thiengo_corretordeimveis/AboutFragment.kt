package thiengo.com.br.thiengo_corretordeimveis


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import mehdi.sakout.aboutpage.AboutPage
import mehdi.sakout.aboutpage.Element


class AboutFragment : Fragment() {

    companion object {
        const val WHATS_APP_NUMBER = "27999887766"
        const val YOU_TUBE_URL = "https://youtube.com/user/thiengoCalopsita"
        const val LINKED_IN_ID = "vinícius-thiengo-5179b180"
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {

        val aboutPage = AboutPage( activity )
            .setImage( R.drawable.thiengo_corretor ) /* 375dp. */
            .setDescription( getString(R.string.about_description) )
            .addItem(
                getItemGroup( R.string.about_contact_group_title )
            )
            .addItem( getItemPhone() )
            .addItem( getItemWhatsApp() )
            .addEmail(
                "thiengocalopsita@gmail.com",
                getString(R.string.about_label_email)
            )
            .addWebsite(
                "https://www.thiengo.com.br",
                getString(R.string.about_label_website)
            )
            .addItem(
                getItemGroup( R.string.about_work_group_title )
            )
            .addItem( getItemYouTube() )
            .addFacebook(
                "thiengoCalopsita",
                getString(R.string.about_label_facebook)
            )
            .addTwitter(
                "thiengoCalops",
                getString(R.string.about_label_twitter)
            )
            .addItem( getItemLinkedIn() )
            .create()

        /*
         * Colocando o posicionamento do texto de descrição à
         * esquerda, sendo que o padrão é no centro.
         * */
        val description = aboutPage.findViewById<TextView>( R.id.description )
        description.gravity = Gravity.START

        /*
         * Acessando o ImageView principal da AboutPage API
         * para remover a margem de topo e colocar uma margem
         * de 16dp no fundo.
         * */
        val image = aboutPage.findViewById<ImageView>( R.id.image )
        val layoutParams = image.layoutParams as LinearLayout.LayoutParams
        layoutParams.topMargin = 0
        layoutParams.bottomMargin = (16 * activity!!.resources.getDisplayMetrics().density).toInt() /* Colocando em DP. */

        return aboutPage
    }

    private fun getItemGroup( titleId: Int )
        = Element()
            .setTitle( getString( titleId ) )
            .setGravity( Gravity.CENTER )

    private fun getItemPhone()
        = Element()
            .setTitle( getString(R.string.about_phone_title) )
            .setIconTint( R.color.colorPhoneLogo )
            .setGravity( Gravity.START )
            .setIconDrawable( R.drawable.ic_phone_in_talk_black_24dp )
            .setOnClickListener {

                val intent = Intent( Intent.ACTION_DIAL )
                intent.setData( Uri.parse("tel:$WHATS_APP_NUMBER") )
                startActivity( intent )
            }

    private fun getItemWhatsApp()
        = Element()
            .setTitle( getString(R.string.about_whats_app_title) )
            .setIconTint( R.color.colorWhatsAppLogo )
            .setGravity( Gravity.START )
            .setIconDrawable( R.drawable.ic_whatsapp_black_24dp )
            .setOnClickListener {

                (activity as MainActivity).whatsAppHelp()
            }

    private fun getItemYouTube()
        = Element()
            .setTitle( getString(R.string.about_label_youtube) )
            .setIconTint( R.color.about_youtube_color )
            .setIconDrawable( R.drawable.about_icon_youtube )
            .setOnClickListener {
                var intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse( YOU_TUBE_URL )
                )
                intent.setPackage( "com.google.android.youtube" )

                /*
                 * Caso o aplicativo no YouTube não esteja
                 * instalado, abre o canal via navegador
                 * mobile.
                 * */
                if( intent.resolveActivity( activity!!.packageManager ) == null ){
                    intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse( YOU_TUBE_URL )
                    )
                }

                activity!!.startActivity( intent )
            }

    private fun getItemLinkedIn()
        = Element()
            .setTitle( getString(R.string.about_linked_in_title) )
            .setIconTint( R.color.colorLinkedInLogo )
            .setGravity( Gravity.START )
            .setIconDrawable( R.drawable.ic_linkedin_box_black_24dp )
            .setOnClickListener {
                var intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse( "linkedin://profile/$LINKED_IN_ID" )
                )
                intent.setPackage( "com.linkedin.android" )

                /*
                 * Caso o aplicativo no LinkedIn não esteja
                 * instalado, abre o perfil via navegador
                 * mobile.
                 * */
                if( intent.resolveActivity( activity!!.packageManager ) == null ){
                    intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse( "http://www.linkedin.com/profile/view?id=$LINKED_IN_ID" )
                    )
                }

                activity!!.startActivity( intent )
            }

    /*
     * Método responsável por conter o algoritmo que invoca
     * o código de atualização de título da atividade.
     * */
    override fun onResume() {
        super.onResume()
        (activity as MainActivity)
            .updateActivityTitle( getString( R.string.about_title ) )
    }
}
