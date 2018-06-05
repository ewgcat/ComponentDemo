package com.yijian.staff.mvp.main.work.face;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import java.util.List;

import io.fotoapparat.facedetector.FaceDetector;
import io.fotoapparat.facedetector.Rectangle;
import io.fotoapparat.facedetector.processor.FaceDetectorProcessor;
import io.fotoapparat.preview.Frame;
import io.fotoapparat.preview.FrameProcessor;

public class MyFaceDetectorProcessor implements FrameProcessor {

    private static Handler MAIN_THREAD_HANDLER = new Handler(Looper.getMainLooper());

    private final FaceDetector faceDetector;
    private final MyFaceDetectorProcessor.OnFacesDetectedListener listener;

    private MyFaceDetectorProcessor(MyFaceDetectorProcessor.Builder builder) {
        faceDetector = FaceDetector.create(builder.context);
        listener = builder.listener;
    }

    public static MyFaceDetectorProcessor.Builder with(Context context) {
        return new MyFaceDetectorProcessor.Builder(context);
    }

    @Override
    public void processFrame(final Frame frame) {
        final List<Rectangle> faces = faceDetector.detectFaces(
                frame.image,
                frame.size.width,
                frame.size.height,
                frame.rotation
        );

        MAIN_THREAD_HANDLER.post(new Runnable() {
            @Override
            public void run() {
                listener.onFacesDetected(faces, frame);
            }
        });
    }

    /**
     * Notified when faces are detected.
     */
    public interface OnFacesDetectedListener {

        /**
         * Null-object for {@link FaceDetectorProcessor.OnFacesDetectedListener}.
         */
        MyFaceDetectorProcessor.OnFacesDetectedListener NULL = new MyFaceDetectorProcessor.OnFacesDetectedListener() {
            @Override
            public void onFacesDetected(List<Rectangle> faces, Frame frame) {
                // Do nothing
            }
        };

        /**
         * Called when faces are detected. Always called on the main thread.
         *
         * @param faces detected faces. If no faces were detected - an empty list.
         */
        void onFacesDetected(List<Rectangle> faces, Frame frame);

    }

    /**
     * Builder for {@link FaceDetectorProcessor}.
     */
    public static class Builder {

        private final Context context;
        private MyFaceDetectorProcessor.OnFacesDetectedListener listener = MyFaceDetectorProcessor.OnFacesDetectedListener.NULL;

        private Builder(Context context) {
            this.context = context;
        }

        /**
         * @param listener which will be notified when faces are detected.
         */
        public MyFaceDetectorProcessor.Builder listener(MyFaceDetectorProcessor.OnFacesDetectedListener listener) {
            this.listener = listener != null
                    ? listener
                    : MyFaceDetectorProcessor.OnFacesDetectedListener.NULL;

            return this;
        }

        public MyFaceDetectorProcessor build() {
            return new MyFaceDetectorProcessor(this);
        }

    }

}
