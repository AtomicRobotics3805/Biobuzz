{
  "startPoint": {
    "x": 62.4,
    "y": 7.2,
    "locked": false,
    "headingDeg": 90
  },
  "lines": [
    {
      "id": "line-mv8ps4jn-quhav6",
      "color": "#ffc516",
      "name": "Path 1",
      "locked": false,
      "waitBeforeMs": 0,
      "waitAfterMs": 0,
      "waitBeforeName": "",
      "waitAfterName": "",
      "kind": "atomic",
      "endPoint": {
        "x": 60,
        "y": 70.75
      },
      "controlPoints": [],
      "heading": {
        "type": "piecewise",
        "startDeg": 270,
        "endDeg": 270,
        "piecewiseHeading": {
          "segments": [
            {
              "startProgress": 0,
              "endProgress": 0.8865814372328455,
              "interpolationType": "tangential",
              "reversed": false,
              "continueFromPrevious": false
            },
            {
              "startProgress": 0.8865814372328455,
              "endProgress": 1,
              "interpolationType": "linear",
              "reversed": false,
              "continueFromPrevious": true,
              "parameters": {
                "startDeg": 0,
                "endDeg": 0
              }
            }
          ]
        },
        "degrees": 180
      }
    }
  ],
  "shapes": [
    {
      "id": "triangle-1",
      "name": "Opposing Side",
      "vertices": [
        {
          "x": 141.5,
          "y": 0
        },
        {
          "x": 141.5,
          "y": 141.5
        },
        {
          "x": 70.75,
          "y": 141.5
        },
        {
          "x": 70.75,
          "y": 0
        }
      ],
      "color": "#dc2626",
      "fillColor": "#ff6b6b"
    },
    {
      "id": "triangle-2",
      "name": "Hive Leg",
      "vertices": [
        {
          "x": 51,
          "y": 48
        },
        {
          "x": 51,
          "y": 93
        },
        {
          "x": 45,
          "y": 93
        },
        {
          "x": 45,
          "y": 48
        }
      ],
      "color": "#2563eb",
      "fillColor": "#60a5fa"
    }
  ],
  "sequence": [
    {
      "kind": "path",
      "lineId": "line-mv8ps4jn-quhav6"
    }
  ],
  "fieldPoints": [],
  "version": "1.5.0",
  "timestamp": "2026-10-17T05:43:27.889Z"
}