package com.eden.util;


import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImageUtil {
	private static final Logger log = LoggerFactory.getLogger(ImageUtil.class) ;
	
	public static ByteArrayOutputStream resize(Image srcImage, int targetWidth,
			int targetHeight) {

		try {
			// 得到图片的原始大小， 以便按比例压缩。
			int imageWidth = srcImage.getWidth(null);
			int imageHeight = srcImage.getHeight(null);
			log.info("图片原始分辩率 W:" + imageWidth + ",H:" + imageHeight ) ;
			log.info("图片压缩后分辩率 W:" + targetWidth + ",H:" + targetHeight ) ;
			// 得到合适的压缩大小，按比例。
			if (imageWidth >= imageHeight) {
				targetHeight = (int) Math
						.round((imageHeight * targetWidth * 1.0 / imageWidth));
			} else {
				targetWidth = (int) Math
						.round((imageWidth * targetHeight * 1.0 / imageHeight));
			}

			// 构建图片对象
			BufferedImage targeImage = new BufferedImage(targetWidth, targetHeight,
					BufferedImage.TYPE_INT_RGB);

			// 绘制缩小后的图
			targeImage.getGraphics().drawImage(srcImage, 0, 0, targetWidth,
					targetHeight, null);
			// 输出到文件流
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(bout);
			encoder.encode(targeImage);
			return bout;
		} catch (Exception e) {
			log.error("resize image Resolution error ", e) ;
			return null ;
		}
	}
	
	/**
	 * 加水印 type=1右下 type=2 左下
	 * @param srcImage
	 * @param waterMarkImgPath
	 * @return
	 */
	public static BufferedImage watermark(BufferedImage srcImage , String waterMarkImgPath , int type) {
		try{
			BufferedImage image = srcImage ;
			File waterMarkImgFile = new File(Thread.currentThread().getContextClassLoader().getResource(waterMarkImgPath).getFile() );
			BufferedImage waterMarkImg = ImageIO.read(waterMarkImgFile) ;
			
//			int x = image.getWidth() - waterMarkImg.getWidth()  ; 
//			int y = image.getHeight() - waterMarkImg.getHeight()  ;
			
			int imageWidth = image.getWidth()  ; 
			int imageHeight = image.getHeight() ;
			
			int x , y , waterImgWidth , waterImgHeight ;
			if(imageWidth < imageHeight ) {
				waterImgWidth = imageWidth * 2 / 10  ;
			}
			else {
				waterImgWidth = imageHeight * 2 / 10  ;
			}
			waterImgHeight = waterImgWidth ;
			
			x = imageWidth - waterImgWidth ;
			y = imageHeight - waterImgHeight ;
			
			//右下
			if(type == 1){
				image.createGraphics().drawImage(waterMarkImg, x , y , waterImgWidth , waterImgHeight , null );
			}
			
			if(type == 2 ){
				x = 0 ;
//				image.createGraphics().drawImage(waterMarkImg, null, x, y );
				image.createGraphics().drawImage(waterMarkImg, x , y , waterImgWidth , waterImgHeight , null );
			}
			
			return image ;
		}catch(Exception e ){
			log.error("water mark image error " , e ) ;
			return null ;
		}
	}
	
	
	
	
	/**
	 * 判断是否是图片文件
	 * 
	 * @param o
	 * @return
	 */
	public static boolean isImageFile(File f) {
		if (f.exists()) {
			try {
				ImageInputStream iis = ImageIO.createImageInputStream(f);
				Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
				if (!iter.hasNext()) {
					return false;
				}
				ImageReader reader = (ImageReader) iter.next();
				iis.close();
				String suf = ("" + reader.getFormatName()).toLowerCase();

				String[] sufs = { "jpg", "gif", "bmp", "png", "jpeg" };
				for (String s : sufs) {
					if (s.equals(suf))
						return true;
				}
			} catch (Exception e) {
				return false;
			}
		} else {
			return false;
		}
		return false;
	}
	
	 public final static ByteArrayOutputStream compress(Image srcImage){
			try {
				// 得到图片的原始大小， 以便按比例压缩。
				int imageWidth = srcImage.getWidth(null);
				int imageHeight = srcImage.getHeight(null);
				int targetHeight = imageHeight ;
				int targetWidth = imageWidth ;
				
				log.info("图片原始分辩率 W:" + imageWidth + ",H:" + imageHeight ) ;
				log.info("图片压缩后分辩率 W:" + targetWidth + ",H:" + targetHeight ) ;

				// 构建图片对象
				BufferedImage targeImage = new BufferedImage(targetWidth, targetHeight,
						BufferedImage.TYPE_INT_RGB);

				// 绘制缩小后的图
				targeImage.getGraphics().drawImage(srcImage, 0, 0, targetWidth,
						targetHeight, null);
				// 输出到文件流
				ByteArrayOutputStream bout = new ByteArrayOutputStream();
				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(bout);
				encoder.encode(targeImage);
				return bout;
			} catch (Exception e) {
				log.error("resize image Resolution error ", e) ;
				return null ;
			}
		}
	 
	 public static byte[] compress(byte[] bytes){
		 try {
			Image image = ImageIO.read(new ByteArrayInputStream(bytes)) ;
			ByteArrayOutputStream bout = compress(image) ;
			return bout.toByteArray() ;
		} catch (Exception e) {
			log.info("compress image error ") ;
			return null ;
		}
	 }
	 
	public static void main(String[] args) throws IOException {
		/*Image image = ImageIO.read(new File("E:\\temp\\imageCompress\\1347791943573.jpg"));
		
		ByteArrayOutputStream bout = ImageUtil.resize(image, 214, 214) ;
		System.out.println(System.currentTimeMillis() - start + "ms") ;
		
		FileOutputStream fout = new FileOutputStream("E:\\temp\\imageCompress\\abc.jpg") ;
		fout.write(bout.toByteArray()) ;
		fout.close() ;
		bout.close() ;*/
		
		BufferedImage image = ImageIO.read(new File("E:\\temp\\imageCompress\\2011-11-02 07.59.59.JPG"));
		
		BufferedImage watermarkedImage = watermark(image, "img/sy.png" , 1) ;
		ImageIO.write(watermarkedImage, "jpg", new FileOutputStream("E:\\temp\\imageCompress\\586456701.jpg")) ;
		
	}
}
