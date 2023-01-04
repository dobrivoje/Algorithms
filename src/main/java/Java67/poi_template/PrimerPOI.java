package Java67.poi_template;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.config.ConfigureBuilder;
import com.deepoove.poi.data.Includes;
import com.deepoove.poi.data.PictureRenderData;
import com.deepoove.poi.data.Pictures;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PrimerPOI {

	static final String desktopFolder = "C:\\Users\\dobrivoje.prtenjak\\OneDrive - Hyperoptic Limited\\"
			+ "Desktop\\poi-tl\\chambers-v2\\";

	public static void main(String[] args) throws IOException {
		String filePath = "singleChamber-header.docx";
		String chamberDetailsNested = "singleChamber-details.docx";
		String addressDetailsNested = "address-details.docx";
		String chamberDetailsSliciceNested = "singleChamber-slicice-details.docx";

		ConfigureBuilder builder = Configure.builder();
		builder.buildGramer( "${", "}" );

		try (XWPFTemplate template = XWPFTemplate.compile( desktopFolder + filePath, builder.build() )) {
			template.render( new HashMap<String, Object>() {
				{

					PictureRenderData picMajcica =
							Pictures.ofLocal( "src/main/resources/slike/regular/majcica.png" )
									.fitSize().create();
					PictureRenderData picAca =
							Pictures.ofLocal( "src/main/resources/slike/regular/aca1.png" )
									/*.fitSize()*/.create();

					put( "title", "Chamber test" );
					put( "feederAndClusterDetails", "feder 111231" );
					put( "surveyor", "surveyor 222" );

					List<BasicPairDto> slike =
							Stream.of( BasicPairDto.Of( "sl.1", picMajcica ),
									   BasicPairDto.Of( "sl.2", picAca ) )
								  .collect( Collectors.toList() );
					// put( "photos", slike );

					List<BasicPairDto> elements =
							Stream.of( BasicPairDto.Of( 1, "chamber - 1", null, picMajcica ),
									   BasicPairDto.Of( 2, "chamber - 2", null, picAca ) )
								  .collect( Collectors.toList() );

					put( "chamberDetails", Includes.ofLocal( desktopFolder + chamberDetailsNested )
												   .setRenderModel( elements ).create() );

					List<ImageModel> images = Stream.of( new ImageModel( "sl. 1", picMajcica ),
														 new ImageModel( "sl. 2", picAca ) )
													.collect( Collectors.toList() );
					put( "slicice", Includes.ofLocal( desktopFolder + chamberDetailsSliciceNested )
											.setRenderModel( images ).create() );


					List<AddrModel> subData = new ArrayList<>();
					subData.add( new AddrModel( "Hangzhou,China" ) );
					subData.add( new AddrModel( "Shanghai,China" ) );
					put( "addressDetails", Includes.ofLocal( desktopFolder + addressDetailsNested )
												   .setRenderModel( subData ).create() );

				}
			} ).writeToFile( desktopFolder + "out_template.docx" );
		} catch (IOException e) {
			throw new RuntimeException( e );
		}

		/*
		try (XWPFTemplate template = XWPFTemplate.compile( desktopFolder + filePath, builder.build() )) {
			template.render( new HashMap<String, Object>() {
				{
					*//*
					put( "title", "Moj prvi test" );
					put( "isImages", true );
					put( "aca", "src/main/resources/slike/regular/aca1.png" );
					put( "majcica", Pictures.ofLocal( "src/main/resources/slike/regular/majcica.png" ).size( 128, 128 ).create() );

					put( "songs", Arrays.asList( BasicDto.Of( "majčica" ), BasicDto.Of( "vladičica" ) ) );
					put( "pairs", Arrays.asList( BasicPairDto.Of( "majčica", 1 ),
												 BasicPairDto.Of( "vladičica", 2 ) ) );

					put( "link", new HyperlinkTextRenderData( "website", "http://deepoove.com" ) );
					put( "anchor", new HyperlinkTextRenderData( "anchortxt", "anchor:appendix1" ) );

					put( "dobri", new TextRenderData( "Dobrivoje", Style.builder()
																		.buildFontFamily( "Amasis MT Pro" )
																		.buildBold().buildColor( "34BBAA" )
																		.buildFontSize( 28 )
																		.build() ) );
					*//*

					PictureRenderData picMajcica =
							Pictures.ofLocal( "src/main/resources/slike/regular/majcica.png" )
									.fitSize().create();
					PictureRenderData picAca =
							Pictures.ofLocal( "src/main/resources/slike/regular/aca1.png" )
									*//*.fitSize()*//*.create();

					put( "title", "Chamber test" );
					put( "feederAndClusterDetails", "feder 111231" );
					put( "surveyor", "surveyor 222" );

					List<BasicPairDto> elements =
							Stream.of( BasicPairDto.Of( 10, "ssss", Arrays.asList( picMajcica ) ),
									   BasicPairDto.Of( 50, Arrays.asList( 4, 5, 6 ) ) )
								  .collect( Collectors.toList() );
					put( "chambers", elements );

					List<BasicPairDto> slike =
							Stream.of( BasicPairDto.Of( "sl.1", picMajcica ),
									   BasicPairDto.Of( "sl.2", picAca ) )
								  .collect( Collectors.toList() );
					put( "photos", slike );

					put( "list", Numberings.create( "Plug-in grammar",
													"Supports word text, pictures, table...",
													"Template, not just template, but also style template" ) );

					// List<PictureRenderData> subData = new ArrayList<>();
					put( "nested", Includes.ofLocal( desktopFolder + nestedPath ).setRenderModel( slike ).create() );
				}
			} ).writeToFile( desktopFolder + "out_template.docx" );
		} catch (IOException e) {
			throw new RuntimeException( e );
		}
		*/

		/*try (XWPFTemplate template = XWPFTemplate.compile( desktopFolder + "template.docx" )) {
			Configure config = Configure.builder().bind( "title", new DocumentRenderPolicy() ).build();
			Map<String, Object> data = new HashMap<>();

			DocumentRenderData document =
					Documents.of().addParagraph( Paragraphs.of( "{{title}}" ).create() )
							 .addParagraph( Paragraphs.of( "{{#table}}" ).create() )
							 .create();
			data.put( "title", document );

			template.render( data );
		} catch (IOException e) {
			throw new RuntimeException( e );
		}*/

		/*
		String text = "this a paragraph";
		DocumentRenderData data = Documents.of().addParagraph( Paragraphs.of( text ).create() ).create();
		try (XWPFTemplate template = XWPFTemplate.create( data )) {
			template.writeToFile( desktopFolder + "out_template.docx" );
		} catch (Exception e) {
			throw new RuntimeException( e );
		}
		*/
	}

}
